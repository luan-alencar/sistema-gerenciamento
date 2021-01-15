package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.exceptions.ResourceNotFoundException;
import com.basis.sge.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;

    // buscar todos
    public List<Usuario> buscarTodos() {
        List<Usuario> list = usuarioRepositorio.findAll();
        list.forEach(record -> {
            if (list.isEmpty()) {
                // Caso o sistema não possua nenhum cadastrado
                throw new ResourceNotFoundException("Sistema sem usuarios cadastrados!", HttpStatus.NOT_FOUND);
            }
        });
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> buscar(Integer id) {
        List<Usuario> list = usuarioRepositorio.findAll();
        list.forEach(record -> {
            // Optional para evitar um NullPointerException
            if (record.getId() == null){
                throw new ResourceNotFoundException("Usuario não encontrado", HttpStatus.NOT_FOUND);
            }
        });
        return usuarioRepositorio.findById(id);
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
