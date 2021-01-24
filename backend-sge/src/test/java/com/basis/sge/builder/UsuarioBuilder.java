package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.Usuario;
import com.basis.sge.servico.UsuarioServico;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Component
public abstract class UsuarioBuilder extends ConstrutorDeEntidade<Usuario> {

    @Autowired
    private UsuarioServico usuarioServico;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public Usuario construirEntidade() throws ParseException {

        Usuario usuario = new Usuario();
        usuario.setNome("usuario de teste");
        usuario.setCpf("12369874563");
        usuario.setEmail("teste@gmail.com");
        usuario.setTelefone("666666666666");
        usuario.setDataNascimento(LocalDate.of(2000,5,5));

        return usuario;
    }

    @Override
    public Usuario persistir(Usuario entidade) {
        usuarioServico.salvar(usuarioMapper.toDto(entidade));
        return  entidade;
    }


    @Override
    public Collection<Usuario> obterTodos() {
        List<UsuarioDTO> lista = usuarioServico.listar();
        List<Usuario> usuarios = usuarioMapper.toEntity(lista);
        return usuarios;
    }

    @Override
    public Usuario obterPorId(Integer id) {
        return usuarioMapper.toEntity(usuarioServico.obterUsuarioPorId(id));
    }
}