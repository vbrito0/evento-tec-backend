package com.eventostec.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventostec.api.entity.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, UUID>{

}
