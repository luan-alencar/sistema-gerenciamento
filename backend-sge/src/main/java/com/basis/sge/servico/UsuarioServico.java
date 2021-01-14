package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServico {

    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> buscarTodos() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> buscar(Integer id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario criar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public void deletar(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }

    public Usuario atualizar(Usuario usuario, Integer id){
        try {
            Usuario usuarioDTO = usuarioRepositorio.getOne(id);
        } catch (Exception e){
            System.err.print(e);
        }
        return usuarioRepositorio.save(usuario);
    }
}
