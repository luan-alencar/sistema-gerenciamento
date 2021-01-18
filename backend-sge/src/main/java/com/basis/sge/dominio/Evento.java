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

    @Column(name = "local")
    private String local;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "qtd_vagas")
    private Integer quantidadeDeVagas;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "id_tipo_evento")
    private List<TipoEvento> idTipoEvento;

    @ManyToMany
    private List<Pergunta> perguntas;
}