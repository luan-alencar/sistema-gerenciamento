package com.basis.sge.servico.dto;


//Classe onde fica as regras de negócios

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PerguntaDTO implements Serializable {

    private static final long SerialVersionIUD = 1L;

    private Integer id;

    private String descricao;


}
