package com.basis.sge.recurso;

import com.basis.sge.dominio.Evento;
import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.dto.EventoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/eventos")
@RequiredArgsConstructor
public class EventoRecurso {

    private final EventoServico eventoServico;

    @GetMapping
    public ResponseEntity<List<EventoDTO>> listar() {
        return ResponseEntity.ok(eventoServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> obterEventoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(eventoServico.obterEventoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Evento> salvar(@RequestBody EventoDTO eventoDTO) {
        eventoServico.salvar(eventoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EventoDTO> editar(@RequestBody EventoDTO eventoDTO) {
        return ResponseEntity.ok(eventoServico.editar(eventoDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        eventoServico.remover(id);
        return ResponseEntity.ok().build();
    }

}