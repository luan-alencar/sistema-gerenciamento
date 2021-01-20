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
    public ResponseEntity<Usuario> buscarUm(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer id) {
        usuarioService.deletar(id);
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