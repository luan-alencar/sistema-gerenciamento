package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "evento_pergunta")
@Getter
@Setter
public class EventoPergunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdEventoPergunta id;
}