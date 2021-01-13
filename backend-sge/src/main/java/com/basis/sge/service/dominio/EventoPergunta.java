package com.basis.sge.service.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Entity
@Table(name = "evento_pergunta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoPergunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdEventoPergunta id;
    // gajjshgdjsgfjk

}
