package com.guardians.dto;

import lombok.Data;

@Data
public class EmployeeResponse {
	private int status;
	private String message;
	private Object data;
}
