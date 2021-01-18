package com.basis.sge.servico.dto;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.InscricaoResposta;
import com.basis.sge.dominio.TipoSituacao;
import com.basis.sge.dominio.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class InscricaoDTO implements Serializable {

    private Integer id;
    private Integer idUsuario;
    private Integer idEvento;
    private Integer tipoSituacao;
    private List<Integer> respostas;
}