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
    private LocalDateTime periodoInicio;
    private LocalDateTime periodoFim;
    private String descricao;
    private String situacao;
    private Integer idSituacao;

    public InscricaoUsuarioDTO(Integer id, String titulo, LocalDateTime inicio, LocalDateTime fim, String desc, String situacao, Integer idSituacao){
        this.id = id;
        this.titulo = titulo;
        this.periodoInicio = inicio;
        this.periodoFim = fim;
        this.descricao = desc;
        this.situacao = situacao;
        this.idSituacao = idSituacao;
    }

}
