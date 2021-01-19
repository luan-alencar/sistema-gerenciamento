package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.Evento;
import com.basis.sge.servico.dto.EventoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {

    @Override
    @Mapping(source = "idTipoEvento", target = "tipoEvento.id")//notacao para mudar o comportamento do metodo
    @Mapping(source = "perguntas", target = "perguntas.id")
    Evento toEntity(EventoDTO eventoDTO);

    @Override
    @Mapping(source = "tipoEvento.id", target = "idTipoEvento")//notacao para mudar o comportamento do metodo
    @Mapping(source = "perguntas.id", target = "perguntas")
    EventoDTO toDto(Evento evento);

}
