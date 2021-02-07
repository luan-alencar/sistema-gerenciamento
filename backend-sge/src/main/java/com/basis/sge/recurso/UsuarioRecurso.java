package com.basis.sge.recurso;

import com.basis.sge.servico.UsuarioServico;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.dto.ChaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioRecurso {

    private final UsuarioServico usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterUsuarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obterUsuarioPorId(id));
    }

    @PostMapping("/login")
    public  ResponseEntity<UsuarioDTO> obterUsuarioPorChave(@RequestBody ChaveDTO chaveDTO){
        return  ResponseEntity.ok(usuarioService.obterPorChave(chaveDTO));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Validated UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuarioDTO));
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> editar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.editar(usuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        usuarioService.remover(id);
        return ResponseEntity.ok().build();
    }
}