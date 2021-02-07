package com.basis.sge.servico;

import com.basis.sge.dominio.*;
import com.basis.sge.repositorio.*;
import com.basis.sge.servico.dto.*;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.InscricaoMapper;
import com.basis.sge.servico.mapper.InscricaoRespostaMapper;
import com.basis.sge.servico.mapper.PerguntaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InscricaoServico {

    private final InscricaoRespostaRepositorio inscricaoRespostaRepositorio;
    private final InscricaoRepositorio inscricaoRepositorio;
    private final InscricaoMapper inscricaoMapper;
    private final EventoRepositorio eventoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final TipoSituacaoRepositorio tipoSituacaoRepositorio;
    private final ProdutorServico produtorServico;
    private final InscricaoRespostaMapper inscricaoRespostaMapper;
    private final PerguntaRepositorio perguntaRepositorio;
    private final PerguntaMapper perguntaMapper;

    private static final Integer ID_SITUACAO_INSCRICAO_CANCELADA = 4;

    // buscar todos
    public List<InscricaoDTO> listar() {
        List<Inscricao> inscricoes = inscricaoRepositorio.findAll();
        return inscricaoMapper.toDto(inscricoes);
    }

    public InscricaoDTO obterInscricaoPorId(Integer id) {
        Inscricao inscricao = inscricaoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Inscrição não existe!"));
        return inscricaoMapper.toDto(inscricao);
    }

    public InscricaoDTO salvar(InscricaoDTO inscricaoDTO) {

        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);

        List<Inscricao> inscricoesUsuario = inscricaoRepositorio.findByUsuario(inscricao.getUsuario());
        for (Inscricao inscricaoUsuario: inscricoesUsuario) {
            if(inscricaoUsuario.getEvento().getId() == inscricaoDTO.getIdEvento()){
                throw new RegraNegocioException("Usuario já inscrito nesse evento");
            }
        }

        List<InscricaoResposta> inscricaoRespostas = inscricao.getResposta();
        inscricao.setResposta(new ArrayList<>());

        inscricaoRepositorio.save(inscricao);

        inscricaoRespostas.forEach(inscricaoResposta ->
                inscricaoResposta.setInscricao(inscricao));

        inscricaoRespostaRepositorio.saveAll(inscricaoRespostas);
        return inscricaoMapper.toDto(inscricao);
    }

    public InscricaoDTO atualizar(InscricaoDTO inscricaoDTO) {

        Inscricao inscricao = inscricaoRepositorio.findById(inscricaoDTO.getId())
                .orElseThrow(() -> new RegraNegocioException("Inscrição não existe"));

        if(!inscricao.getEvento().getId().equals(inscricaoDTO.getIdEvento())
                || !inscricao.getUsuario().getId().equals(inscricaoDTO.getIdUsuario())){
            throw new RegraNegocioException("Não pode ser editado");
        }

        if(!inscricaoDTO.getIdTipoSituacao().equals(inscricao.getIdTipoSituacao().getId())){
            enviarEmailInscricaoEditada(inscricaoDTO);
        }

        inscricaoRepositorio.save(inscricaoMapper.toEntity(inscricaoDTO));

        return inscricaoMapper.toDto(inscricao);
    }

    public void deletar(Integer id) {
        Inscricao inscricao = inscricaoRepositorio.findById(id)
                .orElseThrow(()-> new RegraNegocioException("Id informado não encontrado"));

        enviarEmailInscricaoCancelada(inscricaoMapper.toDto(inscricao));
        inscricaoRepositorio.deleteById(id);
    }

    public List<InscricaoDTO> buscarInscricaoPeloEventoId(Integer id){
        List<InscricaoDTO> inscricoesPeloEventoId = new ArrayList<>();
        List<InscricaoDTO> inscricoes = inscricaoMapper.toDto(inscricaoRepositorio.findAll());
        for(InscricaoDTO inscricao: inscricoes) {
            if(inscricao.getIdEvento().equals(id)){
                inscricoesPeloEventoId.add(inscricao);
            }
        }
        return inscricoesPeloEventoId;
    }

    public void editarInscricaoCancelada(CancelarInscricaoDTO cancelarInscricaoDTO) {
        Usuario usuario = usuarioRepositorio.findByChave(cancelarInscricaoDTO.getChave());

        Inscricao inscricao = inscricaoRepositorio.findById(cancelarInscricaoDTO.getId())
                .orElseThrow(() -> new RegraNegocioException("Não existe inscrição com esse id"));

        if(!inscricao.getUsuario().getId().equals(usuario.getId())){
            throw new RegraNegocioException("Essa Inscricao não é desse usuario");
        }

        inscricao.setIdTipoSituacao(tipoSituacaoRepositorio
                .findById(ID_SITUACAO_INSCRICAO_CANCELADA).orElse(new TipoSituacao()));
        inscricaoRepositorio.save(inscricao);
        enviarEmailInscricaoCancelada(inscricaoMapper.toDto(inscricao));
    }

    public void enviarEmailInscricaoEditada(InscricaoDTO inscricaoDTO){
        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Alteração de inscrição no evento " + inscricao.getEvento().getTitulo());
        emailDTO.setCorpo("Sua inscrição foi avaliada, sua situação é: " + inscricao.getIdTipoSituacao().getDescricao());
        emailDTO.setDestinatario(inscricao.getUsuario().getEmail());
        emailDTO.setCopias(new ArrayList< >());
        emailDTO.getCopias().add(emailDTO.getDestinatario());
        this.produtorServico.enviarEmail(emailDTO);
    }

    public void enviarEmailInscricaoCancelada(InscricaoDTO inscricaoDTO){
        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Cancelamento de inscrição no evento " + inscricao.getEvento().getTitulo());
        emailDTO.setCorpo("Sua inscrição foi cancelada");
        emailDTO.setDestinatario(inscricao.getUsuario().getEmail());
        emailDTO.setCopias(new ArrayList< >());
        emailDTO.getCopias().add(emailDTO.getDestinatario());
        this.produtorServico.enviarEmail(emailDTO);
    }

    public List<ListagemInscricoesDTO> buscarIncricoesPeloEventoId(Integer id){

        Evento evento = eventoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Evento com id não encontrado"));

        List<Inscricao> inscricoes = inscricaoRepositorio.findByEvento(evento);
        List<Pergunta> perguntasDaInscricao = new ArrayList<>();
        List<ListagemInscricoesDTO> listagemInscricoesDTOS = new ArrayList<>();

        for (Inscricao inscricao: inscricoes) {
            ListagemInscricoesDTO listagemInscricoesDTO = new ListagemInscricoesDTO();
            listagemInscricoesDTO.setId(inscricao.getId());
            listagemInscricoesDTO.setNomeUsuario(inscricao.getUsuario().getNome());
            listagemInscricoesDTO.setEmailUsuario(inscricao.getUsuario().getEmail());
            listagemInscricoesDTO.setInscricoesResposta(inscricaoRespostaMapper.toDto(inscricao.getResposta()));
            listagemInscricoesDTO.setIdSituacao(inscricao.getIdTipoSituacao().getId());
            listagemInscricoesDTO.setSituacaoDescricao(inscricao.getIdTipoSituacao().getDescricao());

            for (InscricaoResposta inscricaoResposta: inscricao.getResposta()) {
                Pergunta pergunta = perguntaRepositorio.findById(inscricaoResposta.getPergunta().getId())
                        .orElseThrow(() -> new RegraNegocioException("Pergunta não encontrada"));
                perguntasDaInscricao.add(pergunta);
            }
            listagemInscricoesDTO.setPerguntas(perguntaMapper.toDto(perguntasDaInscricao));

            listagemInscricoesDTOS.add(listagemInscricoesDTO);
            perguntasDaInscricao = new ArrayList<>();
        }

        return listagemInscricoesDTOS;
    }

    public List<InscricaoUsuarioDTO> buscarInscricaoPeloUsuarioId(Integer id){
        List<InscricaoUsuarioDTO> inscricoesPeloUsuarioId = new ArrayList<>();
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuario não encontrado"));
        List<Inscricao> inscricoesUsuario = inscricaoRepositorio.findByUsuario(usuario);


        for (Inscricao inscricoes: inscricoesUsuario) {
            InscricaoUsuarioDTO inscricao = new InscricaoUsuarioDTO(
                    inscricoes.getId(),
                    inscricoes.getEvento().getTitulo(),
                    inscricoes.getEvento().getDataInicio(),
                    inscricoes.getEvento().getDataFim(),
                    inscricoes.getEvento().getDescricao(),
                    inscricoes.getIdTipoSituacao().getDescricao(),
                    inscricoes.getIdTipoSituacao().getId());
            inscricoesPeloUsuarioId.add(inscricao);
        }
        return inscricoesPeloUsuarioId;
    }

}