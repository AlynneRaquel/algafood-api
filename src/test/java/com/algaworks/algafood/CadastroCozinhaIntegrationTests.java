package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIntegrationTests {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Test
	public void testarCadastroCozinhaComSucesso() {
		//cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa2");
		
		//Ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();		
	}
	
	//testando o erro - caminho infeliz
	@Test
	public void testarCadastroCozinhaSemNome() {
	   Cozinha novaCozinha = new Cozinha();
	   novaCozinha.setNome(null);
	   
	   ConstraintViolationException erroEsperado =
	      Assertions.assertThrows(ConstraintViolationException.class, () -> {
	         cadastroCozinha.salvar(novaCozinha);
	      });
	   
	   assertThat(erroEsperado).isNotNull();
	}
	
}
