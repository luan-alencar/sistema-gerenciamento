package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.dto.EventoDTO;
import com.basis.sge.servico.mapper.EventoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        evento.setLocal("Avenida Visconde Sabugosa");
        evento.setTitulo("Arquitetura Limpa");
        evento.setDescricao("Workshop sobre o livro Arquitetura Limpa do autor Robert Cecil Martin");
        evento.setQtdVagas(20);
        evento.setValor(null);
        evento.setDataInicio(LocalDateTime.of(2021, 07, 22, 10, 15, 30));
        evento.setDataFim(LocalDateTime.of(2021, 10, 22, 10, 15, 30));
        evento.setTipoInscricao(false);
        evento.setTipoEvento(tipoEvento);

        return evento;
    }

    @Override
    protected Evento persistir(Evento evento) {
        EventoDTO eventoDTO = eventoServico.salvar(eventoMapper.toDto(evento));
        return eventoMapper.toEntity(eventoDTO);
    }

    @Override
    public List<Evento> obterTodos() {
        return eventoMapper.toEntity(eventoServico.listar());
    }

    @Override
    public Evento obterPorId(Integer id) {
        return eventoMapper.toEntity(eventoServico.obterPorId(id));
    }
}
