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

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;

    // buscar todos
    public Optional<List<UsuarioDTO>> listar() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return Optional.ofNullable(usuarioMapper.toDto(usuarios));
    }

    public Optional<UsuarioDTO> buscar(Integer id) {
        Optional<Usuario> usuario = Optional.of(usuarioRepositorio.getOne(id));
        return usuarioMapper.toDto(usuario);
    }

    public void deletar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public Optional<List<UsuarioDTO>> atualizar(UsuarioDTO usuarioDTO) {
        Usuario usuarioAtualizado = usuarioMapper.toEntity(usuarioDTO);
        usuarioRepositorio.save(usuarioAtualizado);
        return Optional.of(usuarioMapper.toDto((List<Usuario>) usuarioAtualizado));
    }

    @ResponseStatus(HttpStatus.CREATED)
    public Optional<List<UsuarioDTO>> salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepositorio.findByCpf(usuarioDTO.getCpf());
        if (usuario != null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        usuarioRepositorio.save(usuario);
        return Optional.of(usuarioMapper.toDto(Collections.singletonList(usuario)));
    }
}