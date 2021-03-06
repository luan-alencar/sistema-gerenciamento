package com.basis.sge.repositorio;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.servico.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByCpf(String cpf);
    List<Usuario> findByEmail(String email);
    Usuario findByChave(String chave);
}