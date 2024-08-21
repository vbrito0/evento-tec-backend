package com.eventostec.api.entity;

import java.util.Date;
import java.util.UUID;

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

@Table(name = "cupon")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cupon {

	@Id
	@GeneratedValue
	private UUID id;
	private String codigo;
	private Integer desconto;
	private Date valido;
	
	@ManyToOne
	@JoinColumn(name = "evento_id")
	private Evento evento;
}
