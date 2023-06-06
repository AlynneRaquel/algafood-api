package com.algaworks.algafood.di.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	 
	@Autowired//ponto de injeção 3 //(required = false)indica que é opcional ter uma dependência 
	private List<Notificador> notificadores;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		for(Notificador notificador : notificadores) {
		notificador.notificar(cliente, "Seu cadastro está ativo!");
		}
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
