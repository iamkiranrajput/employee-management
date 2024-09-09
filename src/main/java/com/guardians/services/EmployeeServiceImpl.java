package com.guardians.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.guardians.dto.EmployeeDTO;
import com.guardians.dto.EmployeeResponse;
import com.guardians.entity.Employee;
import com.guardians.exception.EmployeeNotFoundException;
import com.guardians.repository.EmployeeRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeServices {
	@Autowired
	private EmployeeRepository empRepository;
	EmployeeResponse employeeResponse = new EmployeeResponse();
	
	
	@Override
	public EmployeeResponse registerEmployee(EmployeeDTO employeeDto) {
		
		try {
			
		log.info("Attempting to register new employee with email :: {}",employeeDto.getEmail());
		
		Employee employee = new Employee();
		employee.setDepartmentId(employeeDto.getDepartmentId());
		employee.setEmpName(employeeDto.getEmpName());
		employee.setDepartmentName(employeeDto.getDepartmentName());
		employee.setCompanyName(employeeDto.getCompanyName());
		employee.setDesignation(employeeDto.getDesignation());
		employee.setSalary(employeeDto.getSalary());
		employee.setEmail(employeeDto.getEmail());
		
		Employee savedEmployee = empRepository.save(employee);
		log.info("Employee Registered Successfully with ID :: {}",savedEmployee.getEmpId());
		employeeResponse.setData(savedEmployee);
		employeeResponse.setMessage("Employee Registered Successfully");
		employeeResponse.setStatus(201);
		}catch(DataIntegrityViolationException ex) {
			log.error("Failed to register employee due to integrity violation :: {}",ex.getMessage());
			  throw new RuntimeException("Employee registration failed. Email might already be in use.");
	    } catch (Exception ex) {
	        log.error("An error occurred during employee registration :: {}", ex.getMessage());
	        throw new RuntimeException("Error occurred while registering the employee. Please try again.");
	    
		}
		return employeeResponse;
	}

	@Override
	public EmployeeResponse getEmployee(Integer employeeId) {
		
		try {
			
			log.info("Fetching employee with ID: {}",employeeId);
			Employee employee = empRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Employee Not Found With ID: "+employeeId));
			
		employeeResponse.setData(employee);
		employeeResponse.setMessage("Employee Data Retrived Successfully");
		employeeResponse.setStatus(HttpStatus.ACCEPTED.value());
		log.info("Employee with ID :: {} Retrival Successfully ",employeeId);
		}catch(EmployeeNotFoundException ex) {
			log.error("Employee with ID {} not found :: {}",employeeId,ex.getMessage());
			throw ex;
			
		}catch(Exception ex) {
			log.error("An Error occured while retriving the Employee :: {} ",ex.getMessage());
			throw new RuntimeException("Error occurred while fetching the employee. Please try again");	
		}
		return employeeResponse;
	}

	@Override
	public EmployeeResponse deleteEmployee(Integer employeeId) {
		
		try {
			log.warn("Attempting to delete employee with id :: {}",employeeId);
		
		empRepository.deleteById(employeeId);
		log.info("Employee with id {} deleted successfully",employeeId);
		employeeResponse.setMessage("Employee Record Deleted");
		employeeResponse.setStatus(HttpStatus.GONE.value());
		}catch(EmptyResultDataAccessException ex) {
			log.error("Employee With Id {} not found for deletion",employeeId);
			throw new EmployeeNotFoundException("Employee Not Found With Id: "+employeeId);
		}catch(Exception ex) {
			log.error("An error occured while deleting employee :: {}",ex.getMessage());
			throw new RuntimeException("Error occured while deleting the employee. please try again");
		}
		return employeeResponse;
	}

	@Override
	public EmployeeResponse updateEmployee(Integer employeeId, EmployeeDTO employeeDto) {
		try {
		log.info("Attempting to update employee with Id :: {}",employeeId);
		empRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found With ID: "+employeeId));
	
		Employee employee = new Employee();
		employee.setDepartmentId(employeeDto.getDepartmentId());
		employee.setEmpName(employeeDto.getEmpName());
		employee.setDepartmentName(employeeDto.getDepartmentName());
		employee.setCompanyName(employeeDto.getCompanyName());
		employee.setDesignation(employeeDto.getDesignation());
		employee.setSalary(employeeDto.getSalary());
		employee.setEmail(employeeDto.getEmail());

		Employee updatedEmployee = empRepository.save(employee);
		log.info("Employee with ID {} updated successfully",employeeId);
		employeeResponse.setData(updatedEmployee);
		employeeResponse.setMessage("Employee Records Updated");
		employeeResponse.setStatus(HttpStatus.ACCEPTED.value());
		}
		catch(EmployeeNotFoundException ex) {
			log.error("Employee with ID {} not found :: {}",employeeId,ex.getMessage());
			throw ex;
		}catch (Exception ex) {
	        log.error("An error occurred while updating employee :: {}", ex.getMessage());
	        throw new RuntimeException("Error occurred while updating the employee. Please try again.");
	    }
		return employeeResponse;
	}

	@Override
	public EmployeeResponse getAllEmployee() {
		try {	
		log.info("Fetching all employee records");
		List<Employee> employeeDetails = empRepository.findAll(); 
		
		if(employeeDetails.isEmpty()) {	
			log.warn("No Employee record Found");
			employeeResponse.setMessage("No Employee Records Found");
			employeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
		}else {
			employeeResponse.setData(employeeDetails);
			employeeResponse.setMessage("Employee Records");
			employeeResponse.setStatus(HttpStatus.ACCEPTED.value());
		}
		}catch(Exception ex){
			log.error("An Error occured while fetching employee :: {}",ex.getMessage());
			throw new RuntimeException("Error occurred while fetching employee records. Please try again");
		}
		return employeeResponse;
	}
}
