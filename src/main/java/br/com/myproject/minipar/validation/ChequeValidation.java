package br.com.myproject.minipar.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.myproject.minipar.models.Cheque;

public class ChequeValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Cheque.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pagador.id", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "cliente.id", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "dataVencimento", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "valor", "field.required");

		//Fazendo um cast do Object para Cartao
//		Cartao cartao = (Cartao) target;

		//Validando e rejeitando quando o campo for menor ou igual a 0
//		if(cartao.getValor() <= new BigDecimal(0)) {
//			errors.rejectValue("valor","field.required");		
//		}

	}
}
