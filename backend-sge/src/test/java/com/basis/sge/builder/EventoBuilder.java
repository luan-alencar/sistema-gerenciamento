package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.dto.EventoDTO;
import com.basis.sge.servico.mapper.EventoMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventoBuilder extends ConstrutorDeEntidade<Evento> {

    @Autowired
    private EventoServico eventoServico;

    @Autowired
    private EventoMapper eventoMapper;

    @Override
    public Evento construirEntidade() throws ParseException {

        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setId(1);

        Evento evento = new Evento();
        evento.setLocal("Avenida Clean Code");
        evento.setTitulo("Arquitetura Limpa");
        evento.setDescricao("Workshop sobre o livro Arquitetura Limpa");
        evento.setQtdVagas(20);
        evento.setValor(10.00);
        evento.setDataInicio(LocalDateTime.of(2021, 07, 22, 10, 15, 30));
        evento.setDataFim(LocalDateTime.of(2021, 10, 22, 10, 15, 30));
        evento.setTipoInscricao(true);
        evento.setIdTipoEvento(tipoEvento);
        evento.setPerguntas(evento.getPerguntas());
        return evento;
    }

    @Override
    protected Evento persistir(Evento evento) {
        EventoDTO eventoDTO = eventoServico.salvar(eventoMapper.toDto(evento));
        return eventoMapper.toEntity(eventoDTO);
    }

    @Override
    protected List<Evento> obterTodos() {
        List<EventoDTO> list = eventoServico.listar();
        return eventoMapper.toEntity(list);
    }

    @Override
    protected Evento obterPorId(Integer id) {
        EventoDTO eventoDTO = eventoServico.obterEventoPorId(id);
        return eventoMapper.toEntity(eventoDTO);
    }
}
