package com.basis.sge.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class PreInscricaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer idUsuario;

    private Integer idEvento;

    private Integer idTipoSituacao;

}
