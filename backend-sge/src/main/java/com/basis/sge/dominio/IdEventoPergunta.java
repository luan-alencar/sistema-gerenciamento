package com.basis.sge.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdEventoPergunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_evento")
    private Evento idEvento;

    @Column(name = "id_pergunta")
    private Pergunta idPergunta;

}
