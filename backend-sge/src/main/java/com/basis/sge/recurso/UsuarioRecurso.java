package com.basis.sge.recurso;

import com.basis.sge.servico.UsuarioServico;
import com.basis.sge.servico.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/login")
    public ResponseEntity<UsuarioDTO> validarLoginUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.validarLoginUsuario(usuarioDTO));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioDTO> obterUsuarioPorCpf(@PathVariable String cpf){
        return ResponseEntity.ok(usuarioService.obterUsuarioPorCpf(cpf));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> obterUsuarioPorEmail(@PathVariable String email){
        return ResponseEntity.ok(usuarioService.obterUsuarioPorEmail(email));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return ResponseEntity.created(URI.create("/usuario"+usuarioDTO.getId())).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        usuarioService.remover(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> editar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.editar(usuarioDTO));
    }
}