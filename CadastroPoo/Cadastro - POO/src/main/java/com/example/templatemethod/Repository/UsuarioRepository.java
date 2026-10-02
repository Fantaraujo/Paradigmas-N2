package com.example.templatemethod.Repository;

import com.example.templatemethod.Entity.Usuario;

import java.util.List;

public interface UsuarioRepository  {

    void cadastrar(Usuario usuario);

    List<Usuario> listar();

    Usuario buscarPorId(String id);

    boolean atualizar(Usuario usuario);

    boolean excluir(String id);
}

