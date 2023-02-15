package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	@Autowired //ponto de injeção 3
	private Notificador notificador;
	
	/*
	 * @Autowired //ponto de injeção 1
	 * public AtivacaoClienteService(Notificador notificador) {
	 *  this.notificador = notificador; }
	 */
	
	/*
	 * public AtivacaoClienteService(String qualquer) { 
	 * }
	 */
	

	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		this.notificador.notificar(cliente, "Seu cadastro está ativo!");
	}
	
	/*
	 * @Autowired //outra forma de injetar 2
	 * public void setNotificador(Notificador notificador) 
	 * { this.notificador = notificador; }
	 */
	
}
