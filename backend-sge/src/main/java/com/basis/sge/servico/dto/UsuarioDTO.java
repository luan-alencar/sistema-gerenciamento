package com.basis.sge.servico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDTO implements Serializable {
    private static final long SerialVersionIUD = 1L;

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String chave;
}
