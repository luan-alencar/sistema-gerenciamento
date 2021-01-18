package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.servico.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {

}
