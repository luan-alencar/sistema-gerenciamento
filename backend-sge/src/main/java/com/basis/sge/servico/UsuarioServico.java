package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.exceptions.ResourceNotFoundException;
import com.basis.sge.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServico {

    private UsuarioRepositorio usuarioRepositorio;
    List<Usuario> list = usuarioRepositorio.findAll();

    public List<Usuario> buscarTodos() {
        list.forEach(record -> {
            if (list.isEmpty()) {
                // Caso o sistema não possua nenhum cadastrado
                throw new ResourceNotFoundException("Sistema sem usuarios cadastrados!", HttpStatus.NOT_FOUND);
            }
        });
        return usuarioRepositorio.findAll();
    }

//    public Usuario buscar(Integer id) {
//        list.forEach(record -> {
//            if (!record){
//
//            }
//        });
//        return usuarioRepositorio.findById(id);
//    }

    public Usuario criar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public void deletar(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }

    public Usuario atualizar(Usuario usuario, Integer id) {
        try {
            Usuario usuarioDTO = usuarioRepositorio.getOne(id);
        } catch (Exception e) {
            System.err.print(e);
        }
        return usuarioRepositorio.save(usuario);
    }
}
