package com.basis.sge.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class InscricaoUsuarioDTO implements Serializable {

    private Integer id;
    private String titulo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String descricao;
    private String situacao;
    private Integer idSituacao;

    public InscricaoUsuarioDTO(Integer id, String titulo, LocalDateTime inicio, LocalDateTime fim, String desc, String situacao, Integer idSituacao){
        this.id = id;
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = desc;
        this.situacao = situacao;
        this.idSituacao = idSituacao;
    }

}