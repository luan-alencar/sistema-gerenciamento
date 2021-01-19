package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class IdInscricaoResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Inscricao idInscricao;
    private Evento idEvento;
    private Pergunta idPergunta;
    private TipoSituacao tipoSituacao;
//    private String resposta;
}
