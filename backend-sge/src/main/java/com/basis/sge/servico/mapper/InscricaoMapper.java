package com.basis.sge.servico.mapper;


import com.basis.sge.dominio.Inscricao;
import com.basis.sge.servico.dto.PreInscricaoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PreInscricaoMapper extends EntityMapper<PreInscricaoDTO, Inscricao> {


}
