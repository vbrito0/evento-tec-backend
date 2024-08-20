package com.eventostec.api.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "EVENTO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private UUID id;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "IMAGEM_URL")
	private String imgUrl;
	
	@Column(name = "EVENTO_URL")
	private String eventoUrl;
	
	@Column(name = "REMOTO")
	private Boolean remoto;
	
	@Column(name = "DATA")
	private Date data;
}
