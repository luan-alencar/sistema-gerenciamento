package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pre_inscricao")
@Setter
@Getter
public class PreInscricao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_pre_inscricao")
    @SequenceGenerator(name = "sequence_pre_inscricao", initialValue = 1, allocationSize = 1, sequenceName = "sq_pre_inscricao")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento idEvento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_situacao")
    private TipoSituacao idTipoSituacao;
}
