package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

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
        return Optional.of(usuarioMapper.toDto(usuario));
    }

    public void deletar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public Optional<UsuarioDTO> atualizar(UsuarioDTO usuarioDTO) {
        Usuario usuarioAtualizado = usuarioMapper.toEntity(usuarioDTO);
        Optional.of(usuarioRepositorio.save(usuarioAtualizado)).orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return Optional.of(usuarioMapper.toDto(usuarioAtualizado));
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) throws RegraNegocioException{
        Usuario usuario = Optional.ofNullable(usuarioRepositorio.findByCpf(usuarioDTO.getCpf()))
                .orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }
}