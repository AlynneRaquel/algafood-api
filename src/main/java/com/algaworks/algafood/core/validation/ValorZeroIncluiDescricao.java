package com.algaworks.algafood.core.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.algaworks.algafood.api.exceptionhandler.MultiploValidator;

@Target({ ElementType.TYPE }) //elementeType.TYPE especifica onde posso usar essa anotação(classe, unum) por exemp
@Retention(RUNTIME)
@Constraint(validatedBy = {ValorZeroIncluiDescricaoValidator.class })
public @interface ValorZeroIncluiDescricao {

	String message() default "Descrição obrigatória inválida";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String valorField();
	
	String descricaoField();
	
	String descricaoObrigatoria(); 
	
}
