package com.basis.sge.recurso;

import com.basis.sge.dominio.PreInscricao;
import com.basis.sge.servico.PreInscricaoServico;
import com.basis.sge.servico.dto.PreInscricaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/inscricoes")
@RequiredArgsConstructor
public class PreInscricaoRecurso {

    private final PreInscricaoServico preInscricaoServico;

    @GetMapping
    public ResponseEntity<List<PreInscricaoDTO>> listar() {
        return ResponseEntity.ok(preInscricaoServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreInscricaoDTO> obterPreInscricaoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(preInscricaoServico.obterPreInscricaoPorId(id));
    }

    @PostMapping
    public ResponseEntity<PreInscricaoDTO> salvar(@RequestBody PreInscricaoDTO preInscricaoDTO) {
        preInscricaoServico.salvar(preInscricaoDTO);
        return ResponseEntity.created(URI.create("/inscricao" + preInscricaoDTO.getId())).build();
    }

    @PutMapping
    public ResponseEntity<PreInscricaoDTO> editar(@RequestBody PreInscricaoDTO preincricaoDTO) {
        return ResponseEntity.ok(preInscricaoServico.atualizar(preincricaoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        preInscricaoServico.deletar(id);
        return ResponseEntity.ok().build();
    }

}

