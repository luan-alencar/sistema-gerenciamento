package com.basis.sge.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "pergunta")
public class Pergunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_pergunta")
    @SequenceGenerator(name = "sequence_pergunta", sequenceName = "sq_pergunta", initialValue = 1, allocationSize = 1)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "obrigatoriedade")
    private Boolean obrigatoriedade;
}
