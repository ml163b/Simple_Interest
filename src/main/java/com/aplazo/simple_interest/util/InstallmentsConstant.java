package com.aplazo.simple_interest.util;

public enum InstallmentsConstant {
	
	UNEXPECTED_ERROR(1000,"Something went wrong, Please contact support."),
	INVALID_INPUT_DATA(2000, "Invalid Input Data.");
	
	private int code;
    private String message;
    
    InstallmentsConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}