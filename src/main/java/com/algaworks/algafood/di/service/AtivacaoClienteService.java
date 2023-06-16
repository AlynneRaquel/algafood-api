package com.algaworks.algafood.di.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;

//@Component
public class AtivacaoClienteService {
	
	@TipoDoNotificador (NivelUrgencia.SEM_URGENCIA)
	@Autowired//ponto de injeção 3 //(required = false)indica que é opcional ter uma dependência 
	private Notificador notificador;
	
//	@PostConstruct
	public void init() {
		System.out.println("INIT" + notificador);
	}
	
//	@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
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
