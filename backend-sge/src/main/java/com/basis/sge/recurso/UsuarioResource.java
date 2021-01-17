package com.basis.sge.recurso;

import com.basis.sge.dominio.Usuario;
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
public class UsuarioResource {

    private final UsuarioServico usuarioService;
    private final UsuarioRepositorio usuarioRepositorio;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUm(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.atualizar(usuarioDTO));
    }
}