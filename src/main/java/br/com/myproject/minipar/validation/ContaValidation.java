package br.com.myproject.minipar.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.myproject.minipar.models.Conta;

public class ContaValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Conta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "numero", "field.required");
	}

}
