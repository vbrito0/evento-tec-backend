package com.eventostec.api.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventostec.api.dto.CuponRequestDTO;
import com.eventostec.api.entity.Cupon;
import com.eventostec.api.entity.Evento;
import com.eventostec.api.repositories.CuponRepository;
import com.eventostec.api.repositories.EventoRepository;

@Service
public class CuponService {

	@Autowired
	private CuponRepository cuponRepository;
	@Autowired
	private EventoRepository eventoRepository;

	public Cupon addCuponToEvento(UUID eventoId, CuponRequestDTO requestDTO) {
		Evento evento = eventoRepository.findById(eventoId).orElseThrow(() -> new IllegalArgumentException("Evento não encontrado!"));
		
		Cupon cupon = new Cupon();
		cupon.setCodigo(requestDTO.codigo());
		cupon.setDesconto(requestDTO.desconto());
		cupon.setValido(new Date(requestDTO.data()));
		cupon.setEvento(evento);
		
		cuponRepository.save(cupon);
		return cupon;
	}

	public List<Cupon> consultarCupon(UUID eventoId, Date currentDate) {
		return cuponRepository.findByEventoIdAndValidoAfter(eventoId, currentDate); 
	}
	
}
