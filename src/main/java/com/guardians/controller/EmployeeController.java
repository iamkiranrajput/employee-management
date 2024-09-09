package com.guardians.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guardians.dto.EmployeeDTO;
import com.guardians.dto.EmployeeResponse;
import com.guardians.services.EmployeeServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
@Slf4j  
public class EmployeeController {
    
    @Autowired
    private EmployeeServices employeeService;
    

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> registerEmployee(@RequestBody EmployeeDTO employeeDto){
        log.info("Recived Post request to Register new employee with email: {}", employeeDto.getEmail());
        EmployeeResponse response = employeeService.registerEmployee(employeeDto);
//        log.info("Employee registered successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Integer employeeId){
        log.info("Recived Get request to Fetch employee with ID: {}", employeeId);
        EmployeeResponse response = employeeService.getEmployee(employeeId);
//        log.info("Employee with ID {} retrieved successfully", employeeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable Integer employeeId){
        log.info("Recived Request to Delete employee with ID: {}", employeeId);
        EmployeeResponse response = employeeService.deleteEmployee(employeeId);
//        log.info("Employee with ID {} deleted successfully", employeeId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);  
    }

    
    
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeDTO employeeDTO){
        log.info("Recived request to update employee with ID :: {}", employeeId);
        EmployeeResponse response = employeeService.updateEmployee(employeeId, employeeDTO);
//        log.info("Employee with ID {} updated successfully", employeeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<EmployeeResponse> getAllEmployee(){
        log.info("Recived request to Fetch all employees");
        EmployeeResponse response = employeeService.getAllEmployee();
//        log.info("All employee records retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
