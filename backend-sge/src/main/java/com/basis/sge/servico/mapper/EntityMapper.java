package com.basis.sge.servico.mapper;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.servico.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface EntityMapper<D, E> {

    E toEntity(D dto);
    Optional<UsuarioDTO> toDto(Optional<Usuario> entity);
    List<E> toEntity(List<D> dtoList);
    List<D> toDto(List<E> entityList);

}