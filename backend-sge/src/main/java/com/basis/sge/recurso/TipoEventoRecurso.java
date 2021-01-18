package com.basis.sge.recurso;

import com.basis.sge.servico.TipoEventoServico;
import com.basis.sge.servico.dto.TipoEventoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tiposeventos")
public class TipoEventoRecurso {

    private final TipoEventoServico tipoEventoServico;

    @GetMapping
    public ResponseEntity<List<TipoEventoDTO>> listar(){
        return ResponseEntity.ok(tipoEventoServico.listar());
    }

    @PostMapping
    public ResponseEntity<TipoEventoDTO> salvar(@RequestBody TipoEventoDTO tipoEventoDTO){
        tipoEventoServico.salvar(tipoEventoDTO);
        return ResponseEntity.created(URI.create("/tipoevento" + tipoEventoDTO.getId())).build();
    }

}