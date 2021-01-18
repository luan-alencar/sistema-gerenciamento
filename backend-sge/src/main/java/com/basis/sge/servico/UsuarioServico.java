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
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = Optional.ofNullable(usuarioRepositorio.findAll()).orElseThrow(() -> new RegraNegocioException("Sistema sem usuarios cadastrados!"));
        return usuarioMapper.toDto(usuarios);
    }

    public UsuarioDTO obterUsuarioPorId(Integer id) {
        Usuario usuarioDTORetorno = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return usuarioMapper.toDto(usuarioDTORetorno);
    }

    public void remover(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    //   ATUALIZAR DADOS
    public UsuarioDTO editar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        Usuario usuarioAtualizado = usuarioMapper.toEntity(usuarioDTO);
        usuarioRepositorio.save(usuarioAtualizado);
        return usuarioMapper.toDto(usuarioAtualizado);
    }

    @Transactional(readOnly = false)
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        Usuario usuario = usuarioRepositorio.findByCpf(usuarioDTO.getCpf());
        usuario.setChave(UUID.randomUUID().toString());
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }
}