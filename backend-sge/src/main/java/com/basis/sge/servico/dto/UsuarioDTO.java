package com.basis.sge.servico.dto;

import com.basis.sge.dominio.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String dataNascimento;
    private String chave;
}
