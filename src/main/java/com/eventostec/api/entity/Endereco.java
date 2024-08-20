package com.eventostec.api.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "ENDERECO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private UUID id;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "UF")
	private String uf;
	
	@ManyToOne
	@JoinColumn(name = "EVENTO_ID")
	private Evento evento;
}
