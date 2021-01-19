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
    private IdInscricaoResposta idInscricaoResposta;

    @MapsId("idInscricao")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inscricao")
    private Inscricao inscricao;
    
    @MapsId("idEvento")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @MapsId("idPergunta")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pergunta")
    private Pergunta pergunta;

    @Column(name = "resposta")
    private String resposta;
}