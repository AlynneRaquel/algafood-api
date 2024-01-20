package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;

public class CozinhaInputDisassembler {

	 @Autowired
	    private ModelMapper modelMapper;
	    
	    public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
	        return modelMapper.map(cozinhaInput, Cozinha.class);
	    }
	    
	    public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
	        modelMapper.map(cozinhaInput, cozinha);
	    }
}
