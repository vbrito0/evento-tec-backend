package com.eventostec.api.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eventostec.api.dto.EventoDetailsDTO;
import com.eventostec.api.dto.EventoRequestDTO;
import com.eventostec.api.dto.EventoResponseDTO;
import com.eventostec.api.entity.Evento;
import com.eventostec.api.service.EventoService;

@RestController
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	private EventoService eventoService;
	
	@PostMapping(path = "/criar", consumes = "multipart/form-data")
	public ResponseEntity<Evento> criar(@RequestParam String titulo, 
										@RequestParam(required = false) String descricao, 
										@RequestParam Long data, 
										@RequestParam String cidade, 
										@RequestParam String estado, 
										@RequestParam Boolean remoto, 
										@RequestParam String eventoUrl, 
										@RequestParam(required = false) MultipartFile imagemUrl){
		EventoRequestDTO requestDTO = new EventoRequestDTO(titulo, descricao, data, cidade, estado, remoto, eventoUrl, imagemUrl);
		Evento novoEvento = eventoService.criarEvento(requestDTO);
		return ResponseEntity.ok(novoEvento);
	}
	
	@GetMapping("/{eventoId}")
	public ResponseEntity<EventoDetailsDTO> eventoDetalhes(@PathVariable UUID eventoId){
		EventoDetailsDTO evento = eventoService.getEventosDetails(eventoId);
		return ResponseEntity.ok(evento);
	}
	
	@GetMapping
	public ResponseEntity<List<EventoResponseDTO>> eventos(@RequestParam(defaultValue = "0") int pag, @RequestParam(defaultValue = "10") int size){
		List<EventoResponseDTO> eventoList = eventoService.getEventosUpcoming(pag, size);
		return ResponseEntity.ok(eventoList);
	}
	
	@GetMapping("/filtrar")
	public ResponseEntity<List<EventoResponseDTO>> filtrarEventos(@RequestParam(defaultValue = "0") int pag, 
																  @RequestParam(defaultValue = "10") int size,
																  @RequestParam(required = false) String titulo,
																  @RequestParam(required = false) String cidade, 
																  @RequestParam(required = false) String estado,
																  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startData,
																  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endData){
		List<EventoResponseDTO> eventoList = eventoService.getEventosFiltrados(pag, size, titulo, cidade, estado, startData, endData);
		return ResponseEntity.ok(eventoList);
	}
}
