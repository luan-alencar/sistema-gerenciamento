package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "inscricao_resposta")
public class InscricaoResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdInscricaoResposta idPreInscricaoResposta;

    @Column(name = "resposta")
    private String resposta;
}