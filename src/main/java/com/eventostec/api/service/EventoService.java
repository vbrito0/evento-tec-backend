package com.eventostec.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.eventostec.api.dto.EventoDetailsDTO;
import com.eventostec.api.dto.EventoDetailsDTO.CuponDTO;
import com.eventostec.api.dto.EventoRequestDTO;
import com.eventostec.api.dto.EventoResponseDTO;
import com.eventostec.api.entity.Cupon;
import com.eventostec.api.entity.Evento;
import com.eventostec.api.repositories.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private AmazonS3 amazonS3Client;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private CuponService cuponService;
	
	@Value("${aws.bucket.name}")
	private String bucketName;
	
	public Evento criarEvento(EventoRequestDTO data) {
		String imagemUrl = null;
		
		if(data.imagemUrl() != null) {
			imagemUrl = uploadImagem(data.imagemUrl());
		}
		
		Evento novoEvento = new Evento();
		novoEvento.setTitulo(data.titulo());
		novoEvento.setDescricao(data.descricao());
		novoEvento.setEventoUrl(data.eventoUrl());
		novoEvento.setData(new Date(data.data()));
		novoEvento.setImagemUrl(imagemUrl);
		novoEvento.setRemoto(data.remoto());
		
		eventoRepository.save(novoEvento);
		
		if(!data.remoto()) {
			enderecoService.criarEndereco(data, novoEvento);
		}
		
		return novoEvento;
	}

	private String uploadImagem(MultipartFile multipartFile) {
		String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
		try {
			File file = convertMultipartToFile(multipartFile);
			amazonS3Client.putObject(bucketName, fileName, file);
			file.delete();
			return amazonS3Client.getUrl(bucketName, fileName).toString();
		} catch (Exception e) {
			System.out.println("Erro ao subir arquivo");
			return "";
		}
	}

	private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
		File convertFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		FileOutputStream fos = new FileOutputStream(convertFile);
		fos.write(multipartFile.getBytes());
		fos.close();
		return convertFile;
	}

	public List<EventoResponseDTO> getEventosUpcoming(int pag, int size) {
		Pageable pageable = PageRequest.of(pag, size);
		Page<Evento> eventoPage = eventoRepository.findEventosEmBreve(new Date(), pageable);
		return eventoPage.map(evento -> new EventoResponseDTO(evento.getId(), 
				  evento.getTitulo(), 
				  evento.getDescricao(), 
				  evento.getData(), 
				  evento.getEndereco() != null ? evento.getEndereco().getCidade() : "", 
				  evento.getEndereco() != null ? evento.getEndereco().getUf() : "", 
				  evento.getRemoto(), 
				  evento.getEventoUrl(), 
				  evento.getImagemUrl())
		)
		.stream().toList();
	}

	public List<EventoResponseDTO> getEventosFiltrados(int pag, int size, String titulo, String cidade, String estado, Date startData, Date endData) {
		titulo = (titulo != null) ? titulo : "";
		cidade = (cidade != null) ? cidade : "";
		estado = (estado != null) ? estado : "";
		startData = (startData != null) ? startData : new Date(0);
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 10);
		endData = (endData != null) ? endData : calendar.getTime();
		
		Pageable pageable = PageRequest.of(pag, size);
		Page<Evento> eventoPage = eventoRepository.findFilteredEventos(titulo, cidade, estado, startData, endData, pageable);
		return eventoPage.map(evento -> new EventoResponseDTO(evento.getId(), 
						  evento.getTitulo(), 
						  evento.getDescricao(), 
						  evento.getData(), 
						  evento.getEndereco() != null ? evento.getEndereco().getCidade() : "", 
						  evento.getEndereco() != null ? evento.getEndereco().getUf() : "", 
						  evento.getRemoto(), 
						  evento.getEventoUrl(), 
						  evento.getImagemUrl())
				)
				.stream().toList();
	}

	public EventoDetailsDTO getEventosDetails(UUID eventoId) {
		Evento evento = eventoRepository.findById(eventoId).orElseThrow(() -> new IllegalArgumentException("Evento não econtrado"));
		
		List<Cupon> cupons = cuponService.consultarCupon(eventoId, new Date());
		
		List<CuponDTO> cuponsDTOs = cupons.stream()
				.map(cupon -> new EventoDetailsDTO.CuponDTO(
						cupon.getCodigo(), 
						cupon.getDesconto(), 
						cupon.getValido()))
				.collect(Collectors.toList());
		
		
		return new EventoDetailsDTO(
				evento.getId(), 
				evento.getTitulo(), 
				evento.getDescricao(), 
				evento.getData(), 
				evento.getEndereco() != null ? evento.getEndereco().getCidade() : "", 
				evento.getEndereco() != null ? evento.getEndereco().getUf() : "",
				evento.getImagemUrl(), 
				evento.getEventoUrl(), 
				cuponsDTOs);
	}
}
