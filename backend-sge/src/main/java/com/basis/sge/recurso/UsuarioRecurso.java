package com.basis.sge.recurso;

import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.UsuarioServico;
import com.basis.sge.servico.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/usuarios", produces = "application/json")
@RestController
@RequiredArgsConstructor
public class UsuarioRecurso {

    private final UsuarioServico usuarioService;
    private final UsuarioRepositorio usuarioRepositorio;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterUsuarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obterUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable("id") Integer id) {
        usuarioService.remover(id);
        return ResponseEntity.ok().build();
    }

    // editar db
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        if (!usuarioRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioDTO.setId(id);
        usuarioDTO = usuarioService.atualizar(usuarioDTO);
        return ResponseEntity.ok(usuarioDTO);
    }
}