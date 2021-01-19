package com.basis.sge.dominio;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipo_situacao")
@Setter
@Getter
public class TipoSituacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", columnDefinition = "VARCHAR(255)")
    private String descricao;
}