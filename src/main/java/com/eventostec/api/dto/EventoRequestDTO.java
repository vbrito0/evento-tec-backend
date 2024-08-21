package com.eventostec.api.dto;

import org.springframework.web.multipart.MultipartFile;

public record EventoRequestDTO(String titulo, String descricao, Long data, String cidade, String estado, Boolean remoto, String eventoUrl, MultipartFile imagemUrl) {

}
