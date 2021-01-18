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
        Optional<Usuario> usuarioDTORetorno = usuarioRepositorio.findById(id);
        if (usuarioDTORetorno.isPresent()) {
            return usuarioDTORetorno.get();
        }
        throw new RegraNegocioException("Usuario não existe!");
    }

    public void deletar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    // atualizar dados
    public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        Usuario newUser = buscar(usuarioDTO.getId());
        updateData(newUser, usuarioDTO);
        usuarioRepositorio.save(newUser);
        return usuarioMapper.toDto(newUser);
    }

    // vai no db e seta o nome, email e telefone ao novo obj
    private void updateData(Usuario newUser, UsuarioDTO user) {
        newUser.setNome(user.getNome());
        newUser.setEmail(user.getNome());
        newUser.setEmail(user.getTelefone());
    }

    @Transactional(readOnly = false)
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        Usuario usuario = buscar(usuarioDTO.getId());
        usuarioMapper.toEntity(usuarioDTO);
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }
}