package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "inscricao_resposta")
public class InscricaoResposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdInscricaoResposta id;

    @Column(name = "resposta")
    private String resposta;
}