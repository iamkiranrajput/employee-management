package com.guardians.services;

import java.util.List;

import com.guardians.dto.EmployeeDTO;
import com.guardians.dto.EmployeeResponse;

public interface EmployeeServices {
	EmployeeResponse registerEmployee(EmployeeDTO employeeDto);
	EmployeeResponse getEmployee(Integer employeeId);
	EmployeeResponse getAllEmployee();
	EmployeeResponse deleteEmployee(Integer employeeId);
	EmployeeResponse updateEmployee(Integer employeeId, EmployeeDTO employeeDto);
	
}
