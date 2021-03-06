package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.repositorio.TipoEventoRepositorio;
import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.mapper.EventoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class EventoBuilder extends ConstrutorDeEntidade<Evento> {

    @Autowired
    private EventoServico eventoServico;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private PerguntaBuilder perguntaBuilder;

    @Autowired
    private TipoEventoRepositorio tipoEventoRepositorio;

    @Override
    public Evento construirEntidade() throws ParseException {

        Evento evento = new Evento();
        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setId(1);
        tipoEventoRepositorio.save(tipoEvento);

        evento.setLocal("Avenida Visconde Sabugosa");
        evento.setTitulo("Arquitetura Limpa");
        evento.setDescricao("Workshop sobre o livro Arquitetura Limpa do autor Robert Cecil Martin");
        evento.setQtdVagas(20);
        evento.setValor(10.0);
        evento.setPerguntas(new ArrayList<>());
        evento.setDataInicio(LocalDateTime.of(2021, 07, 22, 10, 15, 30));
        evento.setDataFim(LocalDateTime.of(2021, 10, 22, 10, 15, 30));
        evento.setTipoInscricao(true);
        evento.setTipoEvento(tipoEvento);
        return evento;
    }

    @Override
    public Evento persistir(Evento evento) {
        eventoServico.salvar(eventoMapper.toDto(evento));
        return evento;
    }

    @Override
    public Collection<Evento> obterTodos() {
        return eventoMapper.toEntity(eventoServico.listar());
    }

    @Override
    public Evento obterPorId(Integer id) {
        return eventoMapper.toEntity(eventoServico.obterEventoPorId(id));
    }
}