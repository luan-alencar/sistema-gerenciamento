package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "evento_pergunta")
@Getter
@Setter
public class EventoPergunta implements Serializable { // é a chave estrangeira dessa tabela

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdEventoPergunta id;

    @MapsId("idEvento")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @MapsId("idPergunta")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pergunta")
    private Pergunta pergunta;

}
