package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

public class RestauranteInputDisassembler {

	public Restaurante toDomainObject(RestauranteInput restautanteInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restautanteInput.getNome());
		restaurante.setTaxaFrete(restautanteInput.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restautanteInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}
}
