package com.basis.sge.builder;

import com.basis.sge.dominio.Inscricao;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;

@Component
public class InscricaoBuilder extends ConstrutorDeEntidade<Inscricao> {

    @Override
    protected Inscricao construirEntidade() throws ParseException {
        return null;
    }

    @Override
    protected Inscricao persistir(Inscricao entidade) {
        return null;
    }

    @Override
    protected Collection<Inscricao> obterTodos() {
        return null;
    }

    @Override
    protected Inscricao obterPorId(Integer id) {
        return null;
    }
}
