package com.example.templatemethod.Service;

import com.example.templatemethod.Entity.Usuario;
import com.example.templatemethod.Repository.UsuarioRepository;
import com.example.templatemethod.Repository.UsuarioRepositoryImpl;

import java.util.List;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService() {
        this.repository = new UsuarioRepositoryImpl();
    }

    public boolean cadastrar(Usuario usuario) {

        if (usuario == null) {
            return false;
        }

        if (usuario.getId() == null || usuario.getId().isBlank()) {
            return false;
        }

        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            return false;
        }

        if (repository.buscarPorId(usuario.getId()) != null) {
            return false;
        }

        repository.cadastrar(usuario);

        return true;
    }

    public List<Usuario> listar() {
        return repository.listar();
    }

    public Usuario buscarPorId(String id) {

        if (id == null || id.isBlank()) {
            return null;
        }

        return repository.buscarPorId(id);
    }

    public boolean atualizar(Usuario usuario) {

        if (usuario == null) {
            return false;
        }

        if (usuario.getId() == null || usuario.getId().isBlank()) {
            return false;
        }

        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            return false;
        }

        if (repository.buscarPorId(usuario.getId()) == null) {
            return false;
        }

        return repository.atualizar(usuario);
    }

    public boolean excluir(String id) {

        if (id == null || id.isBlank()) {
            return false;
        }

        if (repository.buscarPorId(id) == null) {
            return false;
        }

        return repository.excluir(id);
    }
}
