package br.com.myproject.minipar.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.myproject.minipar.models.Pagador;

public class PagadorValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Pagador.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "cpf", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "dataNascimento", "field.required");
	}

}
