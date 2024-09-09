package com.guardians.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "Employees")
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private Integer empId;
	
	@Column(name="department_id", length = 5 , nullable = false)
	private Long departmentId;
	
	@Column(name="emp_name", nullable=false)
	private String empName;
	
	@Column(name="department_name", nullable=false)
	private String departmentName;
	
	@Column(name="company_name", nullable=false)
	private String companyName;
	
	@Column(name="designation", nullable=false)
	private String designation;
	
	@Digits(integer = 7,fraction=2)
	private BigDecimal salary;
	
	@Email
	@NotBlank
	
	@Column(nullable = false, unique = true)
	private String email;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_date")
	private LocalDateTime regDate;
	
	
	
	

}
