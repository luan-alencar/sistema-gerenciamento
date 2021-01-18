package com.basis.sge.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pre_inscricao")
@Setter
@Getter
public class PreInscricao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pre_inscricao")
    @SequenceGenerator(name = "sq_pre_inscricao", allocationSize = 1, sequenceName = "sq_pre_inscricao")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento idEvento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_situação")
    private TipoSituacao idTipoSituacao;



}
