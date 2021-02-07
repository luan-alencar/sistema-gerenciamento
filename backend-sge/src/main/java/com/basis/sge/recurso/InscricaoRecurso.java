package com.basis.sge.recurso;

import com.basis.sge.servico.InscricaoServico;
import com.basis.sge.servico.dto.CancelarInscricaoDTO;
import com.basis.sge.servico.dto.InscricaoDTO;
import com.basis.sge.servico.dto.InscricaoUsuarioDTO;
import com.basis.sge.servico.dto.ListagemInscricoesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoServico.salvar(inscricaoDTO));
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

    @PutMapping("/cancelar")
    public ResponseEntity<Void> cancelarIncricao(@RequestBody CancelarInscricaoDTO cancelarInscricaoDTO){
        inscricaoServico.editarInscricaoCancelada(cancelarInscricaoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/eventoinscricoes/{id}")
    public ResponseEntity<List<ListagemInscricoesDTO>> buscarInscricaoPeloEventoId(@PathVariable Integer id){
        return ResponseEntity.ok(inscricaoServico.buscarIncricoesPeloEventoId(id));
    }

    @GetMapping("/usuarioinscricoes/{id}")
    public ResponseEntity<List<InscricaoUsuarioDTO>> buscarInscricaoPeloUsuarioId(@PathVariable Integer id){
        return ResponseEntity.ok(inscricaoServico.buscarInscricaoPeloUsuarioId(id));
    }

}