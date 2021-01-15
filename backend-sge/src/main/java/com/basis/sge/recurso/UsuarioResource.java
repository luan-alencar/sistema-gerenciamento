package com.basis.sge.recurso;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.servico.UsuarioServico;
import com.basis.sge.servico.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioServico usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUm(@PathVariable Integer id) {
        usuarioService.buscar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return ResponseEntity.created(URI.create("/usuarios" + usuarioDTO.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.atualizar(usuarioDTO));
    }
}