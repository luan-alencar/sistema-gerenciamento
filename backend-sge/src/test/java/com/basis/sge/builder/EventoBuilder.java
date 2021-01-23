package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;

@Component
public class EventoBuilder extends ConstrutorDeEntidade<Evento> {
    @Override
    protected Evento construirEntidade() throws ParseException {
        return null;
    }

    @Override
    protected Evento persistir(Evento entidade) {
        return null;
    }

    @Override
    protected Collection<Evento> obterTodos() {
        return null;
    }

    @Override
    protected Evento obterPorId(Integer id) {
        return null;
    }
}
