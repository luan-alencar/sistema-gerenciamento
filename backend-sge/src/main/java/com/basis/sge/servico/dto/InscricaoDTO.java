
package com.basis.sge.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class InscricaoDTO implements Serializable {

    private Integer id;

    private Integer idUsuario;

    private Integer idEvento;

    private Integer idTipoSituacao;

    private List<InscricaoRespostaDTO> resposta = new ArrayList<>();
}