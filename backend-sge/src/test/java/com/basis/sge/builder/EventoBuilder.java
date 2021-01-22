package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.dto.EventoDTO;
import com.basis.sge.servico.mapper.EventoMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@Transactional
public class EventoBuilder extends ConstrutorDeEntidade<Evento> {

    @Autowired(required = true)
    private EventoServico eventoServico;

    @Autowired(required = true)
    private EventoMapper eventoMapper;

    @Override
    public Evento construirEntidade() throws ParseException {

        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setId(1);

        Evento evento = new Evento();
        evento.setLocal("Avenida");
        evento.setTitulo("Arquitetura");
        evento.setDescricao("Workshop");
        evento.setQtdVagas(20);
        evento.setValor(null);
        evento.setDataInicio(LocalDateTime.of(2021, 07, 22, 10, 15, 30));
        evento.setDataFim(LocalDateTime.of(2021, 10, 22, 10, 15, 30));
        evento.setTipoInscricao(false);
        evento.setIdTipoEvento(tipoEvento);
        return evento;
    }

    @Override
    protected Evento persistir(Evento evento) {
        EventoDTO eventoDTO = eventoServico.salvar(eventoMapper.toDto(evento));
        return eventoMapper.toEntity(eventoDTO);
    }

    @Override
    protected List<Evento> obterTodos() {
        return eventoMapper.toEntity(eventoServico.listar());
    }

    @Override
    protected Evento obterPorId(Integer id) {
        return eventoMapper.toEntity(eventoServico.obterEventoPorId(id));
    }
}
