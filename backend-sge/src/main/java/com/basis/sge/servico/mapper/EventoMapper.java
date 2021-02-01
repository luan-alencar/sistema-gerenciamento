package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.Evento;
import com.basis.sge.servico.dto.EventoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EventoPerguntaMapper.class})
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {

    @Override
    @Mapping(source = "tipoEvento", target = "tipoEvento.id")
    Evento toEntity(EventoDTO eventoDTO);

    @Override
    @Mapping(source = "tipoEvento.id", target = "tipoEvento")
    EventoDTO toDto(Evento evento);

}
