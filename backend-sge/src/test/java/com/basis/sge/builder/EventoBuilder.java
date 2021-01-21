package com.basis.sge.builder;

import com.basis.sge.servico.dto.EventoDTO;

import java.text.ParseException;
import java.util.List;

public class EventoBuilder extends ConstrutorDeEntidade {
    @Override
    protected EventoDTO construirEntidade() throws ParseException {
        return null;
    }

    @Override
    protected EventoDTO persistir(Object entidade) {
        return null;
    }

    @Override
    protected List<EventoDTO> obterTodos() {
        return null;
    }

    @Override
    protected EventoDTO obterPorId(Long id) {
        return null;
    }
}
