package com.basis.sge.servico.dto;

import com.basis.sge.dominio.IdEventoPergunta;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EventoPerguntaDTO implements Serializable {

    private IdEventoPergunta id;
}