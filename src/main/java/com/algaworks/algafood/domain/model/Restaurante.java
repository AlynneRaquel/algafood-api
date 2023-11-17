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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.core.validation.Multiplo;
import com.algaworks.algafood.core.validation.TaxaFrete;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

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
	
	@NotBlank
	@Column(nullable = false) // NULLABLE é detalhe da tabela do banco , não influencia no beanValidation
	private String nome;
	
	@NotNull
//	@PositiveOrZero(message = "{TaxaFrete.invalida}")// mensagem especifica do ValidationMessages 
	@TaxaFrete //anotação criada 
	@Multiplo(numero = 5)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@Valid //Validando as propriedades de Cozinha
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class) //Convertendo grupos de constraints para validação em cascata
	@NotNull 
	@JsonIgnoreProperties("hibernateLazyInitializer")//@JsonIgnore
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
