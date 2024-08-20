package com.eventostec.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventostec.api.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento, UUID>{

}
