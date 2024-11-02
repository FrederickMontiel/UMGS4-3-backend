package com.miumg.fmontiel.eventos.eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.miumg.fmontiel.eventos.eventos.model.Evento;
import com.miumg.fmontiel.eventos.eventos.model.Persona;
import com.miumg.fmontiel.eventos.eventos.repository.EventoRepository;
import com.miumg.fmontiel.eventos.eventos.repository.PersonaRepository;

import com.miumg.fmontiel.eventos.eventos.exceptions.NotFoundedException;
import com.miumg.fmontiel.eventos.eventos.exceptions.ConflictException;

@RestController
@RequestMapping("/api")
public class EventosController {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/eventos")
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @GetMapping("/eventos/{id}")
    public Evento getEventoById(@PathVariable Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundedException());
    }

    @PostMapping("/eventos")
    public Evento createEvento(@RequestBody Evento evento) {
        eventoRepository.findByNombreAndUbicacionAndFechaEvento(
                evento.getNombre(), evento.getUbicacion(), evento.getFechaEvento()).ifPresent(existingEvento -> {
                    throw new ConflictException("Ya existe un evento con el mismo nombre, ubicación y fecha.");
                });
        return eventoRepository.save(evento);
    }

    @PutMapping("/eventos/{id}")
    public Evento updateEvento(@PathVariable Long id, @RequestBody Evento eventoDetails) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundedException());

        eventoRepository.findByNombreAndUbicacionAndFechaEvento(
                eventoDetails.getNombre(), eventoDetails.getUbicacion(), eventoDetails.getFechaEvento())
                .ifPresent(existingEvento -> {
                    if (!existingEvento.getId().equals(id)) {
                        throw new ConflictException("Ya existe un evento con el mismo nombre, ubicación y fecha.");
                    }
                });

        evento.setNombre(eventoDetails.getNombre());
        evento.setUbicacion(eventoDetails.getUbicacion());
        evento.setDescripcion(eventoDetails.getDescripcion());
        evento.setOrganizador(eventoDetails.getOrganizador());
        evento.setFechaEvento(eventoDetails.getFechaEvento());

        return eventoRepository.save(evento);
    }

    @DeleteMapping("/eventos/{id}")
    public void deleteEvento(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundedException());
        eventoRepository.delete(evento);
    }

    @PostMapping("/eventos/{eventoId}/personas")
    public Persona addPersonaToEvento(@PathVariable Long eventoId, @RequestBody Persona persona) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundedException());

        personaRepository.findByEmailAndEventoId(persona.getEmail(), eventoId)
                .ifPresent(existingPersona -> {
                    throw new ConflictException("El email ya está registrado en este evento.");
                });

        personaRepository.findByTelefonoAndEventoId(persona.getTelefono(), eventoId)
                .ifPresent(existingPersona -> {
                    throw new ConflictException("El teléfono ya está registrado en este evento.");
                });

        personaRepository.findByNombreAndEventoId(persona.getNombre(), eventoId)
                .ifPresent(existingPersona -> {
                    throw new ConflictException("El nombre ya está registrado en este evento.");
                });

        persona.setEvento(evento);
        return personaRepository.save(persona);
    }

    @GetMapping("/eventos/{eventoId}/personas")
    public List<Persona> getPersonasByEvento(@PathVariable Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundedException());

        return evento.getPersonas();
    }

    @DeleteMapping("/eventos/{eventoId}/personas/{personaId}")
    public void removePersonaFromEvento(@PathVariable Long eventoId, @PathVariable Long personaId) {
        eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundedException());

        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new NotFoundedException());

        if (!persona.getEvento().getId().equals(eventoId)) {
            throw new ConflictException();
        }

        personaRepository.delete(persona);
    }

    @PutMapping("/eventos/{eventoId}/personas/{personaId}")
    public Persona updatePersonaInEvento(@PathVariable Long eventoId, @PathVariable Long personaId,
            @RequestBody Persona personaDetails) {
        eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundedException());

        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new NotFoundedException());

        if (!persona.getEvento().getId().equals(eventoId)) {
            throw new ConflictException("La persona no está asociada a este evento.");
        }

        personaRepository.findByEmailAndEventoId(personaDetails.getEmail(), eventoId)
                .ifPresent(existingPersona -> {
                    if (!existingPersona.getId().equals(personaId)) {
                        throw new ConflictException("El email ya está registrado en este evento.");
                    }
                });

        personaRepository.findByTelefonoAndEventoId(personaDetails.getTelefono(), eventoId)
                .ifPresent(existingPersona -> {
                    if (!existingPersona.getId().equals(personaId)) {
                        throw new ConflictException("El teléfono ya está registrado en este evento.");
                    }
                });

        personaRepository.findByNombreAndEventoId(personaDetails.getNombre(), eventoId)
                .ifPresent(existingPersona -> {
                    if (!existingPersona.getId().equals(personaId)) {
                        throw new ConflictException("El nombre ya está registrado en este evento.");
                    }
                });

        persona.setNombre(personaDetails.getNombre());
        persona.setEmail(personaDetails.getEmail());
        persona.setTelefono(personaDetails.getTelefono());

        return personaRepository.save(persona);
    }
}