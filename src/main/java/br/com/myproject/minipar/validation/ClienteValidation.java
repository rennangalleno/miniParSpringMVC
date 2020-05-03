package br.com.myproject.minipar.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.myproject.minipar.models.Cliente;

public class ClienteValidation implements Validator {
	
	//verificando se a instância/objeto recebido tem a assinatura da classe Cliente
	@Override
	public boolean supports(Class<?> clazz) {
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//Validando e rejeitando quando o campo for nulo
		ValidationUtils.rejectIfEmpty(errors, "cpf", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "dataNascimento", "field.required");
		
		//Fazendo um cast do Object para Cliente
//		Cliente cliente = (Cliente) target;
		
		//Validando e rejeitando quando o campo for menor ou igual a 0
//		if(cliente.getSalario() <= 0) {
//			erros.rejectValue("salario","field.required");
//		}
	}

}
