package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;

    // buscar todos
    public Optional<List<UsuarioDTO>> listar() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return ofNullable(usuarioMapper.toDto(usuarios));
    }

    public Optional<UsuarioDTO> buscar(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return usuarioMapper.toDto(Optional.of(usuario));
    }

    public void deletar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public Optional<UsuarioDTO> atualizar(UsuarioDTO usuarioDTO) {
        Usuario usuarioAtualizado = usuarioMapper.toEntity(usuarioDTO);
        Optional.of(usuarioRepositorio.save(usuarioAtualizado)).orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return usuarioMapper.toDto(Optional.of(usuarioAtualizado));
    }

    public Optional<UsuarioDTO> salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepositorio.findByCpf(usuarioDTO.getCpf());
        if (usuario == null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        Optional.of(usuarioRepositorio.save(usuario)).orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return usuarioMapper.toDto(Optional.of(usuario));
    }
}