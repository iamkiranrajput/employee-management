package com.guardians.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class EmployeeDTO {
	
	private Long empId;
	private Long departmentId;
	private String empName;
	private String departmentName;
	private String companyName;
	private String designation;
	private BigDecimal salary;
	private String email;
}
