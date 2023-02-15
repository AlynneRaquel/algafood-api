package com.algaworks.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

@Controller //classe responsável por receber requisições web
public class MeuPrimeiroController {
	
	private AtivacaoClienteService ativacaoClienteSevice;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteSevice) {
		this.ativacaoClienteSevice = ativacaoClienteSevice;
		
		System.out.println("MeuPrimeiroController: " + ativacaoClienteSevice);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		Cliente joao = new Cliente("Joao", "joao@email.com", "87999914185");
		ativacaoClienteSevice.ativar(joao);
		return "Hello, Alynne!";
	}

}
