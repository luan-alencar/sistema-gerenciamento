package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.TipoSituacao;
import com.basis.sge.servico.dto.TipoSituacaoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoSituacaoMapper extends EntityMapper<TipoSituacaoDTO, TipoSituacao> {
}
