package com.basis.sge.servico.dto;

import com.basis.sge.dominio.TipoEvento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EventoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String local;

    private String titulo;

    private String descricao;

    private Integer qtdVagas;

    private Double valor;

    private Date dataInicio;

    private Date dataFim;

    private List<TipoEvento> idTipoEvento;

}
