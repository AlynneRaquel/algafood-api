package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.core.validation.Multiplo;
import com.algaworks.algafood.core.validation.TaxaFrete;
import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(
		valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante { /*CLASSE MODELO P/ ESTUDOS, POR ISSO A QUANTIDADE DE COMENTÁRIOS*/
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotBlank comentado porque foi colocado no RestauranteInput a validação de entrada
	@Column(nullable = false) // NULLABLE é detalhe da tabela do banco , não influencia no beanValidation
	private String nome;
	
	//@NotNull
	//@PositiveOrZero(message = "{TaxaFrete.invalida}")// mensagem especifica do ValidationMessages 
	//@TaxaFrete //anotação criada 
	//@Multiplo(numero = 5)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	//@JsonIgnoreProperties(value = "nome", allowGetters = true) //ignora o campo nome no payload do json enviado, ignora apenas quando a chamada for pela url da restaurante
	//@Valid //Validando as propriedades de Cozinha
	//@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class) //Convertendo grupos de constraints para validação em cascata
	//@NotNull 
	//@JsonIgnoreProperties("hibernateLazyInitializer")//@JsonIgnore
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	//@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	//@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	//@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataAtualizacao;
	
	//@JsonIgnore
	@ManyToMany //(fetch = FetchType.EAGER) //Pode gerar problemas
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"), //usar sempre o nome da tabela que recebe o mapeamento
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) // Outra tabela do relacionamento
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();   
	
}
