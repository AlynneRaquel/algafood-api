package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping
	public List<Cidade> listar () {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		return cadastroCidade.buscarOuFalhar(cidadeId);
	}

	@PostMapping
	public Cidade adicionar(@RequestBody Cidade cidade) {
		
		try {
			return cadastroCidade.salvar(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		try {
			return cadastroCidade.salvar(cidadeAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}


	@DeleteMapping("/{cidadeId}")
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
}
