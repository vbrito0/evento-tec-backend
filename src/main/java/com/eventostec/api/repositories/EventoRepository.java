package com.eventostec.api.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventostec.api.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento, UUID>{

	@Query("SELECT ev FROM Evento ev LEFT JOIN FETCH ev.endereco ed WHERE ev.data >= :currentDate")
	Page<Evento> findEventosEmBreve(@Param("currentDate")Date currentDate, Pageable pageable);

	@Query("SELECT ev FROM Evento ev " +
		       "LEFT JOIN ev.endereco ed " +
		       "WHERE (:titulo IS NULL OR ev.titulo LIKE %:titulo%) AND " +
		       "(:cidade IS NULL OR ed.cidade LIKE %:cidade%) AND " +
		       "(:uf IS NULL OR ed.uf LIKE %:uf%) AND " +
		       "ev.data >= :startDate AND " +
		       "ev.data <= :endDate")
		Page<Evento> findFilteredEventos(@Param("titulo") String titulo,
		                                 @Param("cidade") String cidade,
		                                 @Param("uf") String uf,
		                                 @Param("startDate") Date startDate,
		                                 @Param("endDate") Date endDate,
		                                 Pageable pageable);
}
