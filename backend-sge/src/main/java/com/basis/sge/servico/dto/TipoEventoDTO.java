package com.basis.sge.servico.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class TipoEventoDTO implements Serializable {

    private Integer id;

    private String descricao;

}
