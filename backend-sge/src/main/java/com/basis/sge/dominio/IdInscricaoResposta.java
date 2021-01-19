package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class IdInscricaoResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Inscricao idInscricao;
    @ManyToOne
    private Evento idEvento;
    @ManyToOne
    private Pergunta idPergunta;
    @ManyToOne
    private TipoSituacao tipoSituacao;

}
