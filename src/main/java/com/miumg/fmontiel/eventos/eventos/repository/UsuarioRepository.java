package com.miumg.fmontiel.eventos.eventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.miumg.fmontiel.eventos.eventos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombre(@Param("nombre") String nombre);

    List<Usuario> findByEmail(@Param("email") String email);

    @SuppressWarnings("unchecked")
    Usuario save(Usuario usuario);
}
