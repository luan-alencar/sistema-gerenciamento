package com.basis.sge.recurso;


import com.basis.sge.servico.PerguntaServico;
import com.basis.sge.servico.dto.PerguntaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

//Classe onde fica as rotas das urls | Injetar PerguntaServiço
//Exemplo: private final PerguntaServiço ps, perguntaServico.listar e etc

@RestController
@RequestMapping("/api/perguntas")
@RequiredArgsConstructor //
public class PerguntaRecurso {

    private final PerguntaServico perguntaServico;


    @GetMapping
    public ResponseEntity <List<PerguntaDTO>> listar(){

        return ResponseEntity.ok(perguntaServico.listar()); //Recebe do serviço a lista dto e encaminha para quem chamou em json

    }

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaDTO> obterPerguntaporId(@PathVariable Integer id){ //informação vem da rota

       return  ResponseEntity.ok(perguntaServico.buscarPerguntaId(id));

    }

    @PostMapping
    public ResponseEntity<PerguntaDTO> salvar(@RequestBody PerguntaDTO perguntaDTO){


          perguntaServico.salvar(perguntaDTO);
          return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity <PerguntaDTO> editar(@RequestBody PerguntaDTO perguntaDTO){

        return  ResponseEntity.ok(perguntaServico.atualizar(perguntaDTO));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PerguntaDTO> remover(@PathVariable Integer id){

        perguntaServico.deletar(id);
        return ResponseEntity.ok().build();


    }






}
