package com.eventostec.api.entity;

import java.util.Date;
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

@Table(name = "CUPON")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cupon {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private UUID id;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "DESCONTO")
	private Integer desconto;
	
	@Column(name = "VALIDO")
	private Date valido;
	
	@ManyToOne
	@JoinColumn(name = "EVENTO_ID")
	private Evento evento;
}
