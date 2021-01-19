package com.basis.sge.servico.dto;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.Inscricao;
import com.basis.sge.dominio.Pergunta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscricaoRespostaDTO {

    private Inscricao inscricao;
    private Evento evento;
    private Pergunta pergunta;
    private String resposta;
}
