package com.basis.sge.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_evento")
    @SequenceGenerator(name = "sequence_evento", sequenceName = "sq_evento", initialValue = 1, allocationSize = 1)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "local")
    private String local;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "titulo")
    private String titulo;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "descricao")
    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "qtd_vagas")
    private Integer quantidadeDeVagas;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "valor")
    private Double valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @OneToMany
    @JoinColumn(name = "id_tipo_evento")
    private List<TipoEvento> idTipoEvento;

}
