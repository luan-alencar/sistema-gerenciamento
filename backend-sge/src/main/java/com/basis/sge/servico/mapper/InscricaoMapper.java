package com.basis.sge.servico.mapper;


<<<<<<< HEAD:backend-sge/src/main/java/com/basis/sge/servico/mapper/InscricaoMapper.java
import com.basis.sge.dominio.Inscricao;
import com.basis.sge.servico.dto.InscricaoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface InscricaoMapper extends EntityMapper<InscricaoDTO, Inscricao> {
=======
import com.basis.sge.dominio.Inscricao;
import com.basis.sge.servico.dto.PreInscricaoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PreInscricaoMapper extends EntityMapper<PreInscricaoDTO, Inscricao> {
>>>>>>> 499b47b1dea641a0bb24ba440bc1567c647fd59b:backend-sge/src/main/java/com/basis/sge/servico/mapper/InscricaoMapper.java


}
