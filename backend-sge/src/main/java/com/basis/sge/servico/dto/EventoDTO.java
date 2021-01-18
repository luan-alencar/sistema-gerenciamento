package com.basis.sge.servico.dto;

import com.basis.sge.dominio.TipoEvento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EventoDTO implements Serializable {

    private Integer id;

    private String local;

    private String titulo;

    private String descricao;

    private Integer quantidadeDeVagas;

    private Double valor;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private Integer tipoEvento;

}
