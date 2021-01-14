package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "pre_inscricao_resposta")
public class PreInscricaoResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    private PreInscricao id_pre_inscricao;

    private Evento id_evento;

    private Pergunta id_pergunta;
}