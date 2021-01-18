package com.basis.sge.recurso;

import com.basis.sge.servico.TipoEventoServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tiposeventos")
public class TipoEventoRecurso {

    private final TipoEventoServico tipoEventoServico;

    public ResponseEntity<?> listar(){
        tipoEventoServico.listar();
        return ResponseEntity.ok().build();
    }
}