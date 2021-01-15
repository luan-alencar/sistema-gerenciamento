package com.basis.sge.recurso;

import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.dto.EventoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/eventos")
@RequiredArgsConstructor
public class EventoRecurso {

    private final EventoServico eventoServico;

    @GetMapping
    public ResponseEntity<List<EventoDTO>> listar(){
        return ResponseEntity.ok(eventoServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> pegarEventoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(eventoServico.pegarEventoPorId(id));
    }

    @PostMapping
    public ResponseEntity<EventoDTO> salvar(@RequestBody EventoDTO eventoDTO){
        eventoServico.salvar(eventoDTO);
        return ResponseEntity.created(URI.create("/evento"+eventoDTO.getId())).build();
    }

    @PutMapping
    public ResponseEntity<EventoDTO> editar(@RequestBody EventoDTO eventoDTO){
        return ResponseEntity.ok(eventoServico.editar(eventoDTO));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> remover(@PathVariable Integer id){
        eventoServico.remover(id);
        return ResponseEntity.ok().build();
    }

}
