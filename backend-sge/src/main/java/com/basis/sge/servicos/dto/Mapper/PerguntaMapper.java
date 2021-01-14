package com.basis.sge.servicos.dto.Mapper;

import com.basis.sge.dominio.Pergunta;
import com.basis.sge.servicos.dto.PerguntaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PerguntaMapper extends EntityMapper<PerguntaDTO, Pergunta> {



}
