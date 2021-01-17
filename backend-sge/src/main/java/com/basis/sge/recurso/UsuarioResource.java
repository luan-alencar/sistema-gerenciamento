package com.basis.sge.recurso;

import com.basis.sge.servico.UsuarioServico;
import com.basis.sge.servico.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioServico usuarioService;

    @GetMapping
    public ResponseEntity<Optional<List<UsuarioDTO>>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUm(@PathVariable Integer id) {
        usuarioService.buscar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return ResponseEntity.created(URI.create("/usuarios" + usuarioDTO.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.atualizar(usuarioDTO));
    }
}