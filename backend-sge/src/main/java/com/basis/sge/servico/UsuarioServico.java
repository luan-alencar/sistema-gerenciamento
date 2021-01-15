package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;

    // buscar todos
    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarioMapper.toDto(usuarios);
    }

    public UsuarioDTO buscar(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id).get();
        return usuarioMapper.toDto(usuario);
    }

    public void deletar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) {
        Usuario usuarioAtualizado = usuarioMapper.toEntity(usuarioDTO);
        usuarioRepositorio.save(usuarioAtualizado);
        return usuarioMapper.toDto(usuarioAtualizado);
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepositorio.findByCpf(usuarioDTO.getCpf());
        if (usuario != null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }
}