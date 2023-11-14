package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable = false) // NULLABLE é detalhe da tabela do banco , não influencia no beanValidation
	private String nome;
	
	@DecimalMin("1")
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	//@JsonIgnore
	@JsonIgnoreProperties("hibernateLazyInitializer")
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	@ManyToMany //(fetch = FetchType.EAGER) //Pode gerar problemas
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"), //usar sempre o nome da tabela que recebe o mapeamento
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) // Outra tabela do relacionamento
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
}
