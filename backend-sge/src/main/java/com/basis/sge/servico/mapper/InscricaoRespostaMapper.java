package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.InscricaoResposta;
import com.basis.sge.servico.dto.InscricaoRespostaDTO;
import org.mapstruct.Mapping;

public interface InscricaoRespostaMapper extends EntityMapper<InscricaoRespostaDTO, InscricaoResposta> {

    @Override
    @Mapping(source = "idEvento", target = "evento.id")//notacao para mudar o comportamento do metodo
    @Mapping(source = "idInscricao", target = "inscricao.id")
    @Mapping(source = "idPergunta", target = "pergunta.id")
    InscricaoResposta toEntity(InscricaoRespostaDTO inscricaoRespostaDTO);

    @Override
    @Mapping(source = "evento.id", target = "idEvento")//notacao para mudar o comportamento do metodo
    @Mapping(source = "inscricao.id", target = "idInscricao")
    @Mapping(source = "pergunta.id", target = "idPergunta")
    InscricaoRespostaDTO toDto(InscricaoResposta inscricaoResposta);

}
