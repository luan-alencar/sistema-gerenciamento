package com.basis.sge.servico.dto;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.TipoSituacao;
import com.basis.sge.dominio.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;


@Getter
@Setter
public class PreInscricaoDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    private Integer id;

    private Usuario idUsuario;

    private Evento idEvento;

    private TipoSituacao idTipoSituacao;

}
