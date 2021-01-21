package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.dto.EventoDTO;
import com.basis.sge.servico.mapper.EventoMapper;
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

    @Autowired
    private EventoMapper eventoMapper;

    @Override
    public EventoDTO construirEntidade() throws ParseException {

        Evento evento = new Evento();
        evento.setLocal("Avenida Clean Code");
        evento.setTitulo("Arquitetura Limpa");
        evento.setDescricao("Workshop sobre o livro Arquitetura Limpa");
        evento.setQtdVagas(20);
        evento.setValor(null);
        evento.setDataInicio(null);
        evento.setDataFim(null);
        evento.setTipoInscricao(false);
        evento.setIdTipoEvento(evento.getIdTipoEvento());
        evento.setPerguntas(evento.getPerguntas());
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
