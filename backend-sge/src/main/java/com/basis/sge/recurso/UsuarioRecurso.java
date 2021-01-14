package com.basis.sge.recurso;

import com.basis.sge.servico.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioRecurso {

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUm(@PathVariable Integer id){
        return null;
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarTodos(){
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody UsuarioDTO usuarioDTO){
        return null;
    }

}
