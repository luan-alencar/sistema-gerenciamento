package com.basis.sge.recurso;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.servico.UsuarioServico;
import com.basis.sge.servico.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioResource {

    private UsuarioServico usuarioService;
    List<Usuario> list = usuarioService.buscarTodos();

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        List<UsuarioDTO> usuarioDTOList = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUm(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        return null;
    }
}