package com.basis.sge.servico.dto;

import com.basis.sge.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDTO implements Serializable {
    private static final long SerialVersionIUD = 1L;

    private Integer id;
    @NotBlank(message = "{nome.not.blank}")
    private String nome;
    @NotBlank(message = "{cpf.not.blank}")
    private String cpf;
    @NotBlank(message = "{email.not.blank}")
    private String email;
    @NotBlank(message = "{telefone.not.blank}")
    private String telefone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dataNascimento;
    private String chave;
}
