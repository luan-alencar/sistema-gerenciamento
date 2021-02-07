package com.basis.sge.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ListagemInscricoesDTO implements Serializable {

    private Integer id;

    private String nomeUsuario;

    private String emailUsuario;

    private String situacaoDescricao;

    private Integer idSituacao;

    private List<PerguntaDTO> perguntas;

    private List<InscricaoRespostaDTO> inscricoesResposta;

}