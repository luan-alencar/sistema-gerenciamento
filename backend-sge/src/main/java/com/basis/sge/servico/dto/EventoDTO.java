package com.basis.sge.servico.dto;

import com.basis.sge.dominio.EventoPergunta;
import com.basis.sge.dominio.TipoEvento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EventoDTO implements Serializable {

    private Integer id;

    private Integer local;
    private Integer titulo;
    private Integer descricao;
    private Integer qtdVagas;
    private Double valor;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Boolean tipoInscricao;
    private Integer tipoEvento;
    private List<EventoPergunta> perguntas;
}
