package com.basis.sge.dominio;

import com.basis.sge.servico.dto.EventoPerguntaDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "inscricao")
@Setter
@Getter
public class Inscricao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_inscricao")
    @SequenceGenerator(name = "sq_inscricao", allocationSize = 1, initialValue = 1, sequenceName = "sq_inscricao")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento")
    private Evento idEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_situacao")
    private TipoSituacao tipoSituacao;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = InscricaoResposta.class, mappedBy = "inscricao")
    private List<EventoPergunta> respostas;
}
