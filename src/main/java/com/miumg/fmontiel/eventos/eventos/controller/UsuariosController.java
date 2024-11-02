package com.miumg.fmontiel.eventos.eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miumg.fmontiel.eventos.eventos.model.Usuario;
import com.miumg.fmontiel.eventos.eventos.repository.UsuarioRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

import com.miumg.fmontiel.eventos.eventos.exceptions.ConflictException;
import com.miumg.fmontiel.eventos.eventos.exceptions.NotFoundedException;

@RestController
@RequestMapping("/api")
public class UsuariosController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/user/login")
    public Usuario login(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = repository.findByEmail(usuario.getEmail());

        if (usuarios.size() == 0) {
            throw new NotFoundedException();
        } else if (usuarios.size() > 1) {
            throw new ConflictException();
        } else {
            BCrypt.Result result = BCrypt.verifyer().verify(usuario.getPassword().toCharArray(),
                    usuarios.get(0).getPassword().toCharArray());

            if (result.verified) {
                Usuario user = usuarios.get(0);
                user.setPassword(null);
                return user;
            } else {
                throw new ConflictException();
            }

        }

    }

    @PostMapping("/user/register")
    public Usuario register(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = repository.findByEmail(usuario.getEmail());

        if (usuarios.size() > 0) {
            throw new ConflictException();
        }

        usuario.setPassword(BCrypt.withDefaults().hashToString(12, usuario.getPassword().toCharArray()));

        return repository.save(new Usuario(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTelefono()));
    }
}
