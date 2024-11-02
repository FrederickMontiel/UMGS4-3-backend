package com.miumg.fmontiel.eventos.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.miumg.fmontiel.eventos.eventos.model.Evento;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByNombre(String nombre);

    List<Evento> findByOrganizador(String organizador);

    List<Evento> findByUbicacion(String ubicacion);

    Optional<Evento> findByNombreAndUbicacionAndFechaEvento(String nombre, String ubicacion, Date fechaEvento);
}
