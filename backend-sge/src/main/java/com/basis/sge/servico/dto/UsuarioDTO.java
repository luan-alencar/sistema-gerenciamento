package com.basis.sge.servico.dto;

import com.basis.sge.dominio.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final long SerialVersionIUD = 1L;

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String chave;

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.telefone = obj.getTelefone();
        this.chave = obj.getChave();
    }
}
