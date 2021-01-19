package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "evento_pergunta")
@Getter
@Setter
public class EventoPergunta implements Serializable { // é a chave estrangeira dessa tabela

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdEventoPergunta id;

    @Column(name = "id_evento")
    private Evento idEvento;

    @Column(name = "id_pergunta")
    private Pergunta idPergunta;
}