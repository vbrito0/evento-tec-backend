package com.eventostec.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventostec.api.dto.CuponRequestDTO;
import com.eventostec.api.entity.Cupon;
import com.eventostec.api.service.CuponService;

@RestController
@RequestMapping("/cupon")
public class CuponController {

	@Autowired
	private CuponService cuponService;
	
	@PostMapping("evento/{eventoId}")
	public ResponseEntity<Cupon> addCuponToEvento(@PathVariable UUID eventoId, @RequestBody CuponRequestDTO requestDTO){
		Cupon cupon = cuponService.addCuponToEvento(eventoId, requestDTO);
		return ResponseEntity.ok(cupon);
	}
}
