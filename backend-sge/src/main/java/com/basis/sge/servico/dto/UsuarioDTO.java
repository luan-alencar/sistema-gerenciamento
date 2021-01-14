package com.basis.sge.servico.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioDTO implements Serializable {
    private static final long SerialVersionIUD = 1L;

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String chave;
}
