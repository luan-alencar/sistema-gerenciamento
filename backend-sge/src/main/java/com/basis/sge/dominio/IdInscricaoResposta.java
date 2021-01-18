package com.basis.sge.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class IdInscricaoResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Inscricao idInscricao;

    private Evento idEvento;

    private Pergunta idPergunta;

}
