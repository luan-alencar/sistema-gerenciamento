package com.basis.sge.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipo_evento")
@Getter
@Setter
public class TipoEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_reserva")
    @SequenceGenerator(name = "sq_reserva", sequenceName = "sq_reserva", initialValue = 1, allocationSize = 1)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

}
