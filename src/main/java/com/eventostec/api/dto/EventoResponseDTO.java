package com.eventostec.api.dto;

import java.util.Date;
import java.util.UUID;

public record EventoResponseDTO(UUID id, String titulo, String descricao, Date data, String cidade, String estado, Boolean remoto, String eventoUrl, String imagemUrl) {

}
