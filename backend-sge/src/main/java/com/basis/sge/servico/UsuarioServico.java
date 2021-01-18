package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Usuario buscar(Integer id) {
        return getUsuarioId(id);
    }

    public void deletar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        Usuario usuarioAtualizado = getUsuario(usuarioDTO); // método extraido
        usuarioRepositorio.save(usuarioAtualizado);
        return usuarioMapper.toDto(usuarioAtualizado);
    }

    @Transactional(readOnly = false)
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO)  throws RegraNegocioException{
        Usuario usuarioAtualizado = getUsuario(usuarioDTO); // método extraido
        usuarioRepositorio.save(usuarioAtualizado);
        return usuarioMapper.toDto(usuarioAtualizado);
    }

    // métodos extraidos
    private Usuario getUsuario(UsuarioDTO usuarioDTO) throws RegraNegocioException{
        Usuario usuarioAtualizado = Optional.of(usuarioMapper.toEntity(usuarioDTO))
                .orElseThrow(() -> new RegraNegocioException("Usuario não existe!s"));
        return usuarioAtualizado;
    }
    @NotNull
    private Usuario getUsuarioId(Integer id) {
        Optional<Usuario> usuarioDTORetorno = usuarioRepositorio.findById(id);
        if (usuarioDTORetorno.isPresent()) {
            return usuarioDTORetorno.get();
        }
        throw new RegraNegocioException("Usuario não existe!");
    }
}