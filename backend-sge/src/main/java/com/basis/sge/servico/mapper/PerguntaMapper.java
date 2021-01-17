package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.Pergunta;
import com.basis.sge.servico.dto.PerguntaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PerguntaMapper extends EntityMapper<PerguntaDTO, Pergunta> {



}
