package com.basis.sge.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EventoDTO implements Serializable {

    private Integer id;
    
    private String local;
    private String titulo;
    private String descricao;
    private Integer qtdVagas;
    private Double valor;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Boolean tipoInscricao;
    private Integer tipoEvento;
    private List<EventoPerguntaDTO> perguntas = new ArrayList<>();
}
