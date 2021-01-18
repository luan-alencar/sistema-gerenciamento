package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inscricao")
@Setter
@Getter
public class Inscricao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_inscricao")
    @SequenceGenerator(name = "sequence_inscricao", initialValue = 1, allocationSize = 1, sequenceName = "sq_inscricao")
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
