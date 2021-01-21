package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.dto.EmailDTO;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;
    private final EmailServico emailServico;

    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if(usuarios.isEmpty()){
            throw new RegraNegocioException("Nenhum usuario cadastrado!");
        }
        return usuarioMapper.toDto(usuarios);
    }

    public UsuarioDTO obterUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO obterUsuarioPorCpf(String cpf){
        Usuario usuario = usuarioRepositorio.findByCpf(cpf)
                .orElseThrow(() -> new RegraNegocioException("Cpf não cadastrado"));
        return usuarioMapper.toDto(usuario);
    }

    public void remover(Integer id) {
        usuarioRepositorio.delete(usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Id informado não encontrado")));
    }

    public UsuarioDTO editar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        Usuario usuarioAtualizado = usuarioMapper.toEntity(usuarioDTO);
        usuarioRepositorio.save(usuarioAtualizado);
        return usuarioMapper.toDto(usuarioAtualizado);
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        List<Usuario> usuarioBD = usuarioRepositorio.findAll();
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        for(Usuario lista : usuarioBD){
            if(lista.getCpf().equals(usuario.getCpf())){
                throw new RegraNegocioException("Já existe usuario cadastrado com esse cpf");
            }

            if(lista.getEmail().equals(usuario.getEmail())){
                throw new RegraNegocioException("Já existe usuario cadastrado com esse e-mail");
            }

        }

        int idade = LocalDate.now().getYear() - usuario.getDataNascimento().getYear();
        if (usuario.getDataNascimento().equals(LocalDate.now()) || (idade > 115 || idade < 10)){
            throw new RegraNegocioException("Data de nascimento inválida");
        }

        usuario.setChave(UUID.randomUUID().toString());
        usuarioRepositorio.save(usuario);
        enviarEmail(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public void enviarEmail(Usuario usuario){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Cadastro de usuário");
        emailDTO.setCorpo("<h1> Você foi cadastrado com sucesso na plataforma de evento! </h1> <p>Esta é sua chave de inscrição em eventos: <b>" + usuario.getChave() + "</b> </p>");

        emailDTO.setDestinatario(usuario.getEmail());
        emailServico.sendMail(emailDTO);
    }
}