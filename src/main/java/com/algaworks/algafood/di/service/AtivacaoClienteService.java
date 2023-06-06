package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	 
	@Qualifier("urgente")
	@Autowired//ponto de injeção 3 //(required = false)indica que é opcional ter uma dependência 
	private Notificador notificador;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "Seu cadastro está ativo!");
		
	}
	
	/*
	 * @Autowired //ponto de injeção 1
	 * public AtivacaoClienteService(Notificador notificador) {
	 *  this.notificador = notificador; }
	 */
	
	/*
	 * public AtivacaoClienteService(String qualquer) { 
	 * }
	 */
	
	/*
	 * @Autowired //outra forma de injetar 2
	 * public void setNotificador(Notificador notificador) 
	 * { this.notificador = notificador; }
	 */
	
}
