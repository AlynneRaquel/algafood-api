package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {
	
	@Autowired
	public ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInput restautanteInput) {
		return modelMapper.map(restautanteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		 //para o JPA mapear uma nova instância de cozinha. 
		//para evitar: identifier of an instance of com.algaworks.algafood.domain.model.Cozinha was altered from 2 to 1
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteInput, restaurante);
	}
}
