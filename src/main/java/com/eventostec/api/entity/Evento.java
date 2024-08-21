package com.eventostec.api.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "evento")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
	
	@Id
	@GeneratedValue
	private UUID id;
	private String titulo;
	private String descricao;
	private String imagemUrl;
	private String eventoUrl;
	private Boolean remoto;
	private Date data;
	
	@OneToOne(mappedBy = "evento", cascade = CascadeType.ALL)
	private Endereco endereco;
}
