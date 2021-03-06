package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.InscricaoResposta;
import com.basis.sge.servico.dto.InscricaoRespostaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface InscricaoRespostaMapper extends EntityMapper<InscricaoRespostaDTO, InscricaoResposta> {

    @Override
    @Mapping(source = "idEvento", target = "evento.id")//notacao para mudar o comportamento do metodo
    @Mapping(source = "idInscricao", target = "inscricao.id")
    @Mapping(source = "idPergunta", target = "pergunta.id")

    @Mapping(source = "idPergunta", target = "id.idPergunta")
    @Mapping(source = "idEvento", target = "id.idEvento")
    @Mapping(source = "idInscricao", target = "id.idInscricao")
    InscricaoResposta toEntity(InscricaoRespostaDTO inscricaoRespostaDTO);

    @Override
    @Mapping(source = "evento.id", target = "idEvento")//notacao para mudar o comportamento do metodo
    @Mapping(source = "inscricao.id", target = "idInscricao")
    @Mapping(source = "pergunta.id", target = "idPergunta")
    InscricaoRespostaDTO toDto(InscricaoResposta inscricaoResposta);

}
