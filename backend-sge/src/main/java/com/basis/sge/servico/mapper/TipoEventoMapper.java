package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.servico.dto.TipoEventoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface TipoEventoMapper extends EntityMapper<TipoEventoDTO, TipoEvento> {
}
