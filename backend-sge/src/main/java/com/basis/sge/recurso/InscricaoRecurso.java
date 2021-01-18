package com.basis.sge.recurso;

import com.basis.sge.servico.InscricaoServico;
import com.basis.sge.servico.dto.PreInscricaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/inscricoes")
@RequiredArgsConstructor
public class InscricaoRecurso {

    private final InscricaoServico inscricaoServico;

    @GetMapping
    public ResponseEntity<List<PreInscricaoDTO>> listar() {
        return ResponseEntity.ok(inscricaoServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreInscricaoDTO> obterPreInscricaoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(inscricaoServico.obterPreInscricaoPorId(id));
    }

    @PostMapping
    public ResponseEntity<PreInscricaoDTO> salvar(@RequestBody PreInscricaoDTO preInscricaoDTO) {
        inscricaoServico.salvar(preInscricaoDTO);
        return ResponseEntity.created(URI.create("/inscricao" + preInscricaoDTO.getId())).build();
    }

    @PutMapping
    public ResponseEntity<PreInscricaoDTO> editar(@RequestBody PreInscricaoDTO preincricaoDTO) {
        return ResponseEntity.ok(inscricaoServico.atualizar(preincricaoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        inscricaoServico.deletar(id);
        return ResponseEntity.ok().build();
    }

}