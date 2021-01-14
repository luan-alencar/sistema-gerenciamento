package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "pre_inscricao_resposta")
public class PreInscricaoResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id_pre_inscricao;

    @Id
    private Integer id_evento;

    @Id
    private Integer id_pergunta;
}