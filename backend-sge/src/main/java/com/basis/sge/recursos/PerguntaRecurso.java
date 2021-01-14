package com.basis.sge.recursos;


import com.basis.sge.servicos.dto.PerguntaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

//Classe onde fica as rotas das urls | Injetar PerguntaServiço
//Exemplo: private final PerguntaServiço, perguntaServico.listar e etc

@RestController
@RequestMapping("/api/pergunta")
@RequiredArgsConstructor //
public class PerguntaRecurso {


    @GetMapping("/perguntas")
    public ResponseEntity <PerguntaDTO> buscarTodos(){

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaDTO> buscarUm(@PathVariable Integer id){

        return null;

    }



}
