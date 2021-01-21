package com.basis.sge.builder;

import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.dto.EventoDTO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
public class EventoBuilder extends ConstrutorDeEntidade {

    @Autowired
    private EventoServico eventoServico;

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
        return eventoServico.listar();
    }

    @Override
    protected EventoDTO obterPorId(Long id) {
        return null;
    }
}
