package com.eventostec.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventostec.api.dto.EventoRequestDTO;
import com.eventostec.api.entity.Endereco;
import com.eventostec.api.entity.Evento;
import com.eventostec.api.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco criarEndereco(EventoRequestDTO data, Evento evento) {
		Endereco endereco = new Endereco();
		endereco.setCidade(data.cidade());
		endereco.setUf(data.estado());
		endereco.setEvento(evento);
		
		return enderecoRepository.save(endereco);
	}
}
