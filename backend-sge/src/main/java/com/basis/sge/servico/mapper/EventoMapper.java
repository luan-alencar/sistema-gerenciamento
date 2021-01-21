package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.Evento;
import com.basis.sge.servico.dto.EventoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EventoPerguntaMapper.class})
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {

    @Override
    @Mapping(source = "idTipoEvento", target = "idTipoEvento.id")
//notacao para mudar o comportamento do metodo
    Evento toEntity(EventoDTO eventoDTO);

    @Override
    @Mapping(source = "idTipoEvento.id", target = "idTipoEvento")
//notacao para mudar o comportamento do metodo
    EventoDTO toDto(Evento evento);

}
