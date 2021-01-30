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
    private final ProdutorServico produtorServico;

    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if(usuarios.isEmpty()){
            throw new RegraNegocioException("Nenhum usuario cadastrado!");
        }
        return usuarioMapper.toDto(usuarios);
    }

    public UsuarioDTO obterUsuarioPorId(Integer id) {
        Usuario usuario = obterIdUsuarioException(id);
        return usuarioMapper.toDto(usuario);
    }

    private Usuario obterIdUsuarioException(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
    }

    public UsuarioDTO obterUsuarioPorCpf(String cpf){
        Usuario usuario = usuarioRepositorio.findByCpf(cpf);
        if(usuario == null){
            throw new RegraNegocioException("Cpf não cadastrado");
        }
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO obterUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        if(usuario == null){
            throw new RegraNegocioException("Cpf não cadastrado");
        }
        return usuarioMapper.toDto(usuario);
    }

    public void remover(Integer id) {
        usuarioRepositorio.delete(usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Id informado não encontrado")));
    }

    public UsuarioDTO editar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        validarDadosNull(usuarioDTO);
        validarEmail(usuarioDTO);
        validarCpf(usuarioDTO);
        validarIdade(usuarioDTO);
        Usuario usuarioBD = obter(usuarioDTO.getId());
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setChave(usuarioBD.getChave());
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) throws RegraNegocioException {

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        List<UsuarioDTO> usuarios = usuarioMapper.toDto(usuarioRepositorio.findAll());

        if(usuarios.isEmpty()){
            validarDadosNull(usuarioDTO);
            validarIdade(usuarioDTO);

            usuario.setChave(UUID.randomUUID().toString());
            usuarioRepositorio.save(usuario);
            EmailDTO emailDTO = enviarEmail(usuario);
            produtorServico.enviarEmail(emailDTO);
        }
        else {

            if (usuarioDTO.equals(null)) {
                throw new RegraNegocioException("Usuário null não pode ser cadastrado");
            } else {

                for (UsuarioDTO lista : usuarios) {

                    validarDadosNull(lista);
                    validarCpf(lista);
                    validarEmail(lista);

                    //validarDadosDuplicados(lista);

                }

                validarIdade(usuarioDTO);

                usuario.setChave(UUID.randomUUID().toString());
                usuarioRepositorio.save(usuario);
                EmailDTO emailDTO = enviarEmail(usuario);
                produtorServico.enviarEmail(emailDTO);
            }
        }
        return usuarioMapper.toDto(usuario);
    }

    private void validarDadosNull(UsuarioDTO usuario){
        if(usuario.getNome() == null){
            throw new RegraNegocioException("Nome de usuário não informado");
        }

        if(usuario.getCpf() == null){
            throw new RegraNegocioException("Cpf não informado");
        }

        if(usuario.getEmail() == null){
            throw new RegraNegocioException("E-mail não informado");
        }

        if(usuario.getDataNascimento() == null){
            throw new RegraNegocioException("Data de nascimento não informada");
        }
    }

    private void validarIdade(UsuarioDTO usuario){
        int idade = LocalDate.now().getYear() - usuario.getDataNascimento().getYear();
        if (idade > 115 || idade < 10) {
            throw new RegraNegocioException("Data de nascimento inválida");
        }
    }

    private EmailDTO enviarEmail(Usuario usuario){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Cadastro de usuário");
        emailDTO.setCorpo("<h1> Você foi cadastrado com sucesso na plataforma de evento! </h1> <p>Esta é sua chave de inscrição em eventos: <b>" + usuario.getChave() + "</b> </p>");

        emailDTO.setDestinatario(usuario.getEmail());
        return emailDTO;
    }

    private Usuario obter(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
    }

    private void validarEmail(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepositorio.findByEmail(usuarioDTO.getEmail());
        if(usuario != null && !usuario.getId().equals(usuarioDTO.getId())) {
            throw new RegraNegocioException("Email já cadastrado");
        }
    }

    private void validarCpf(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepositorio.findByCpf(usuarioDTO.getCpf());
        if(usuario != null && !usuario.getId().equals(usuarioDTO.getId())) {
            throw new RegraNegocioException("CPF já cadastrado");
        }
    }


}