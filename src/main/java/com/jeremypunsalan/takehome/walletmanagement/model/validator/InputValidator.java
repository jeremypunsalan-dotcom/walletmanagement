package com.jeremypunsalan.takehome.walletmanagement.model.validator;

import java.util.List;

public interface InputValidator<T> {
	
	List<String> validate(T objectToBeValidated);

}
