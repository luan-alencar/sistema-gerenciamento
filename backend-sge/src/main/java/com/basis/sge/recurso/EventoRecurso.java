package com.basis.sge.recurso;

import com.basis.sge.servico.EventoServico;
import com.basis.sge.servico.dto.EventoDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "/api/eventos")
@RestController
@RequiredArgsConstructor
public class EventoRecurso {

    private final EventoServico eventoServico;

    @GetMapping
    public ResponseEntity<List<EventoDTO>> listar() {
        return new ResponseEntity<>(eventoServico.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> obterEventoPorId(@PathVariable Integer id) {
        EventoDTO entidadeDTO = eventoServico.obterEventoPorId(id);
        return ResponseEntity.ok(entidadeDTO);
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<EventoDTO> salvar(@RequestBody EventoDTO eventoDTO) {
        return ResponseEntity.created(new URI("/api/eventos")).body(eventoServico.salvar(eventoDTO));
    }

    @PutMapping
    public ResponseEntity<EventoDTO> editar(@RequestBody EventoDTO eventoDTO) {
        EventoDTO newEventoDTO = eventoServico.editar(eventoDTO);
        return ResponseEntity.ok(newEventoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        eventoServico.remover(id);
        return ResponseEntity.ok().build();
    }

}