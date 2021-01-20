package com.basis.sge.builder;

import com.basis.sge.dominio.Usuario;

import java.text.ParseException;
import java.util.Collection;

public class UsuarioBuilder extends ConstrutorDeEntidade<Usuario> {
    @Override
    protected Usuario construirEntidade() throws ParseException {
        return null;
    }

    @Override
    protected Usuario persistir(Usuario entidade) {
        return null;
    }

    @Override
    protected Collection<Usuario> obterTodos() {
        return null;
    }

    @Override
    protected Usuario obterPorId(Long id) {
        return null;
    }
}
