package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.exceptions.UserAlreadyExist;
import com.basis.sge.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioCadastro {

    private UsuarioRepositorio usuarioRepositorio;

    public Usuario salvar(Usuario usuario){
        Usuario usuarioExiste = usuarioRepositorio.findByEmail(usuario.getEmail());
        if (usuarioExiste != null && !usuarioExiste.equals(usuario)){
            throw new UserAlreadyExist("Já existe um usário cadastrado com este email!");
        }
        return usuarioRepositorio.save(usuario);
    }

    public void excluir(Integer usuarioId){
        usuarioRepositorio.deleteById(usuarioId);
    }
}
