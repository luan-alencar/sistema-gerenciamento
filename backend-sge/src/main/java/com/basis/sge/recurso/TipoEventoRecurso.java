package com.basis.sge.recurso;

import com.basis.sge.servico.TipoEventoServico;
import com.basis.sge.servico.dto.TipoEventoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tiposeventos")
public class TipoEventoRecurso {

    private final TipoEventoServico tipoEventoServico;

    @GetMapping
    public ResponseEntity<List<TipoEventoDTO>> listar() {
        return ResponseEntity.ok(tipoEventoServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEventoDTO> obterTipoEventoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoEventoServico.obterTipoEventoPorId(id));

    }

}