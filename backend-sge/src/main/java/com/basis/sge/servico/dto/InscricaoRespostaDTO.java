package com.basis.sge.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscricaoRespostaDTO {

    private Integer idInscricao;

    private Integer idEvento;

    private Integer idPergunta;

    private String resposta;
}
