package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.Inscricao;
import com.basis.sge.servico.dto.InscricaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {InscricaoRespostaMapper.class})
public interface InscricaoMapper extends EntityMapper<InscricaoDTO, Inscricao> {

    @Override
    @Mapping(source = "idUsuario", target = "usuario.id")
    @Mapping(source = "idEvento", target = "evento.id")
    @Mapping(source = "idTipoSituacao", target = "idTipoSituacao.id")
    Inscricao toEntity(InscricaoDTO inscricaoDTO);

    @Override
    @Mapping(source = "usuario.id", target = "idUsuario")
    @Mapping(source = "evento.id", target = "idEvento")
    @Mapping(source = "idTipoSituacao.id", target = "idTipoSituacao")
    InscricaoDTO toDto(Inscricao inscricao);

}