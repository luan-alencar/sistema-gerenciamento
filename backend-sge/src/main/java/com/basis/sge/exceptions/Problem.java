package com.basis.sge.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

// notacao JsonInclude fara cm que caso tenha um erro, nao mostrar os campos
// null, ou seja, so incluira campos q nao estejam nulos qnd for serializar em JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Problem {
    private Integer status;
    private String titulo;
    private OffsetDateTime dataHora;
    private List<Campo> campos = new ArrayList<>();

    public Problem(Integer status, String titulo, OffsetDateTime dataHora){
        this.status = status;
        this.titulo = titulo;
        this.dataHora = dataHora;
    }

    public static class Campo {
        private String nome;
        private String mensagem;

        public Campo(String nome, String mensagem) {
            this.nome = nome;
            this.mensagem = mensagem;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

}
