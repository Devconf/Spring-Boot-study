package com.example.SampleWebApp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	boolean onlyNumber= false;
	
	@Override
	public void initialize(Phone phone) {
		onlyNumber= phone.onlyNumber();
	};
	
	@Override	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return false;
		}
		if(this.onlyNumber) {
			return value.matches("[0-9]*");
		}
		else {
			return value.matches("[0-9()-]*");	
		}
	}

}
