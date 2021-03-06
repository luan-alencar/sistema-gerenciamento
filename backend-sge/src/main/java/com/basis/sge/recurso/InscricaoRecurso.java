package com.basis.sge.recurso;

import com.basis.sge.servico.InscricaoServico;
import com.basis.sge.servico.dto.InscricaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/inscricoes")
@RestController
@ComponentScan
@RequiredArgsConstructor
public class InscricaoRecurso {

    private final InscricaoServico inscricaoServico;

    @GetMapping
    public ResponseEntity<List<InscricaoDTO>> listar() {
        return ResponseEntity.ok(inscricaoServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDTO> obterInscricaoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(inscricaoServico.obterInscricaoPorId(id));
    }

    @PostMapping
    public ResponseEntity<InscricaoDTO> salvar(@RequestBody InscricaoDTO inscricaoDTO) {
        inscricaoServico.salvar(inscricaoDTO);
        return ResponseEntity.created(URI.create("/inscricoes" + inscricaoDTO.getId())).build();
    }

    @PutMapping
    public ResponseEntity<InscricaoDTO> editar(@RequestBody InscricaoDTO incricaoDTO) {
        return ResponseEntity.ok(inscricaoServico.atualizar(incricaoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        inscricaoServico.deletar(id);
        return ResponseEntity.ok().build();
    }

}