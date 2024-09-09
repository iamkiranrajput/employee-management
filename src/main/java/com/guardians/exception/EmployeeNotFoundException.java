package com.guardians.exception;

public class EmployeeNotFoundException extends RuntimeException {
	public EmployeeNotFoundException(String e) {
		super(e);
	}
}
