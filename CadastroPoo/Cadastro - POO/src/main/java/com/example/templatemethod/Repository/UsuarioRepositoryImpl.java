package com.example.templatemethod.Repository;

import com.example.templatemethod.Entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private static final List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public Usuario buscarPorId(String id) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }

        return null;
    }

    @Override
    public boolean atualizar(Usuario usuario) {

        Usuario usuarioExistente = buscarPorId(usuario.getId());

        if (usuarioExistente == null) {
            return false;
        }

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());

        return true;
    }

    @Override
    public boolean excluir(String id) {

        Usuario usuario = buscarPorId(id);

        if (usuario == null) {
            return false;
        }

        usuarios.remove(usuario);

        return true;
    }
}
