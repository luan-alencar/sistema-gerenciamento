package com.basis.sge.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class IdPreInscricaoResposta  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_pre_inscricao")
    private PreInscricao idPreInscricao;

    @Column(name = "id_evento")
    private Evento idEvento;

    @Column(name = "id_pergunta")
    private Pergunta idPergunta;

}
