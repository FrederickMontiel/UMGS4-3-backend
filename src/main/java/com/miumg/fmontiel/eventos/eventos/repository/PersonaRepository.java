package com.miumg.fmontiel.eventos.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.miumg.fmontiel.eventos.eventos.model.Persona;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByEmailAndEventoId(String email, Long eventoId);

    Optional<Persona> findByTelefonoAndEventoId(String telefono, Long eventoId);

    Optional<Persona> findByNombreAndEventoId(String nombre, Long eventoId);
}
