package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.InscricaoRepositorio;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.dto.ChaveDTO;
import com.basis.sge.servico.dto.EmailDTO;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;
    private final ProdutorServico produtorServico;
    private final InscricaoRepositorio inscricaoRepositorio;

    public List<UsuarioDTO> listar() {
        return usuarioMapper.toDto(usuarioRepositorio.findAll());
    }

    public UsuarioDTO obterUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO obterPorChave(ChaveDTO chaveDTO){
        Usuario usuario = usuarioRepositorio.findByChave(chaveDTO.getChave());
        if(usuario == null){
            throw  new RegraNegocioException("Não foi possível encontrar a chave informada");
        }
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO){
        usuarioDTO.setTipoUsuario("u");
        Usuario usuario = validarDadosCadastrais(usuarioDTO);
        usuario = usuarioRepositorio.save(usuario);
        enviarEmailCadastro(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO editar(UsuarioDTO usuarioDTO) throws RegraNegocioException {
        Usuario usuario = usuarioRepositorio.save(validarDadosEdicao(usuarioDTO));
        enviarEmailEdicao(usuarioDTO);
        return usuarioMapper.toDto(usuario);
    }

    public void remover(Integer id) {
        if (id == null){
            throw new RegraNegocioException("Id não existe");
        }

        if(!usuarioRepositorio.existsById(id)){
            throw new RegraNegocioException("O usuário não foi cadastrado");
        }

        inscricaoRepositorio.deleteAllByUsuario(usuarioRepositorio.findById(id).orElseThrow(()
                -> new RegraNegocioException("O usuário não foi cadastrado")));
        Usuario usuario = validarDadosRemocao(id);
        enviarEmailRemocao(usuario);
    }

    private Usuario obter(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
    }

    private void enviarEmailCadastro(Usuario usuario){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Cadastro de usuário");
        emailDTO.setCorpo("<h1> Você foi cadastrado com sucesso na plataforma de evento! </h1> <p>Esta é sua chave de inscrição em eventos: <b>" + usuario.getChave() + "</b> </p>");
        emailDTO.setDestinatario(usuario.getEmail());
        emailDTO.setCopias(new ArrayList<>());
        emailDTO.getCopias().add(emailDTO.getDestinatario());
        this.produtorServico.enviarEmail(emailDTO);
    }

    private void enviarEmailEdicao(UsuarioDTO usuarioDTO){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Alteração de dados cadastrais");
        emailDTO.setCorpo("Seu cadastro foi alterado com sucesso!");
        emailDTO.setDestinatario(usuarioDTO.getEmail());
        emailDTO.setCopias(new ArrayList< >());
        emailDTO.getCopias().add(emailDTO.getDestinatario());
        this.produtorServico.enviarEmail(emailDTO);
    }

    private void enviarEmailRemocao(Usuario usuario){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Remoção de cadastro de usuário");
        emailDTO.setCorpo("Seu cadastro foi removido com sucesso!");
        emailDTO.setDestinatario(usuario.getEmail());
        emailDTO.setCopias(new ArrayList<>());
        emailDTO.getCopias().add(emailDTO.getDestinatario());
        this.produtorServico.enviarEmail(emailDTO);
    }

    private Usuario validarDadosCadastrais(UsuarioDTO usuarioDTO){
        if( !usuarioRepositorio.findByEmail(usuarioDTO.getEmail()).isEmpty() ){
            throw new RegraNegocioException("O email já foi cadastrado");
        }

        if(!usuarioRepositorio.findByCpf(usuarioDTO.getCpf()).isEmpty()){
            throw new RegraNegocioException("Cpf já cadastrado");
        }

        //EXCEPTION IDADE ERRADA (OBS: EVENTUALMENTE MUDAR PARA LOCALDATE)
        if (usuarioDTO.getDataNascimento().isAfter(LocalDate.now())){
            throw new RegraNegocioException("Data de nascimento invalida");
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setChave(UUID.randomUUID().toString());
        return usuario;
    }

    public Usuario validarDadosEdicao(UsuarioDTO usuarioDTO){
        if( usuarioDTO.getId() == null){
            throw new RegraNegocioException("Id inválido");
        }

        Usuario usuario = usuarioRepositorio.findById(usuarioDTO.getId())
                .orElseThrow(()-> new RegraNegocioException("Usuario não existe"));

        //CRIA LISTAS ONDE INSTANCIAS COM O MESMO CPF OU EMAIL DO DTO, REMOVENDO O USUARIO QUE VAI SER MODIFICADO//
        List<Usuario> listaCpf = usuarioRepositorio.findByCpf(usuarioDTO.getCpf());
        List<Usuario> listaEmail = usuarioRepositorio.findByEmail(usuarioDTO.getEmail());
        //REMOVE USUARIO
        listaCpf.remove(usuario);
        listaEmail.remove(usuario);



        //SET


        // VERIFICAR CPF NULL

        if(usuarioDTO.getCpf() == null){
            throw new RegraNegocioException("CPF Nulo");
        }

        if(usuarioDTO.getDataNascimento() == null){
            throw new RegraNegocioException("Data de nascimento nula.");
        }

        if(usuarioDTO.getEmail() == null){
            throw new RegraNegocioException("Email nulo");
        }

        if(usuarioDTO.getNome() == null){
            throw new RegraNegocioException("Nome nulo");
        }

        if(!listaEmail.isEmpty()){
            throw new RegraNegocioException("Email já cadastrado");
        }

        if(!listaCpf.isEmpty()){
            throw new RegraNegocioException("CPF já cadastrado");
        }


        // EXCEPTION CPF INVALIDO
        if (usuarioDTO.getCpf().length() != 14){
            throw new RegraNegocioException("CPF invalido");
        }


        //EXCEPTION IDADE ERRADA

        if (usuarioDTO.getDataNascimento().isAfter(LocalDate.now())){
            throw new RegraNegocioException("Data de nascimento invalida.");
        }

        if (usuarioDTO.getTelefone().length() > 14){
            throw new RegraNegocioException("Numero invalido");
        }
        Usuario usuarioTemp = usuarioMapper.toEntity(usuarioDTO);
        usuarioTemp.setChave(usuario.getChave());
        return  usuarioTemp;
    }

    public Usuario validarDadosRemocao(Integer id){
        Usuario usuario = obter(id);
        usuarioRepositorio.deleteById(id);
        return usuario;
    }

}