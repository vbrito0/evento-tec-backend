package com.eventostec.api.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record EventoDetailsDTO(UUID id, String titulo, String descricao, Date data, String cidade, String estado,
		String imagemUrl, String eventoUrl, List<CuponDTO> cupons) {
	
	public record CuponDTO(String codigo, Integer desconto, Date valido) {
	}

}