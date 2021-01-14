package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pre_inscricao")
@Setter
@Getter
public class pre_inscricao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pre_inscricao")
    @SequenceGenerator(name = "pre_inscricao", allocationSize = 1, sequenceName = "sq_pre_inscricao")
    private Integer id;

    @Column(name = "id_usuario")
    @ManyToMany
    private Integer id_usuario;

    @Column(name = "id_evento")
    @ManyToMany
    private Integer id_evento;

    @Column(name = "id_tipo_situacao")

    private Integer id_tipo_situacao;
}
