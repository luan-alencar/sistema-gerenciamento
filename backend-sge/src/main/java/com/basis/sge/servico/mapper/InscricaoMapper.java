package com.basis.sge.servico.mapper;


import com.basis.sge.dominio.PreInscricao;
import com.basis.sge.servico.dto.InscricaoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface InscricaoMapper extends EntityMapper<InscricaoDTO, PreInscricao> {


}
