package com.basis.sge.servico;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.EventoPergunta;
import com.basis.sge.dominio.Pergunta;
import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.EventoPerguntaRepositorio;
import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.repositorio.InscricaoRepositorio;
import com.basis.sge.repositorio.PerguntaRepositorio;
import com.basis.sge.servico.dto.EmailDTO;
import com.basis.sge.servico.dto.EventoDTO;
import com.basis.sge.servico.dto.InscricaoDTO;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServico {

    private final EventoRepositorio eventoRepositorio;
    private final EventoPerguntaRepositorio eventoPerguntaRepositorio;
    private final PerguntaRepositorio perguntaRepositorio;
    private final InscricaoRepositorio inscricaoRepositorio;
    private final InscricaoServico inscricaoServico;
    private final UsuarioServico usuarioServico;
    private final EventoMapper eventoMapper;
    private final EmailServico emailServico;

    public List<EventoDTO> listar() {
        List<Evento> lista = eventoRepositorio.findAll();
        return eventoMapper.toDto(lista);

    }

    public EventoDTO obterEventoPorId(Integer id) {
        Evento evento = eventoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Id informado não encontrado"));
        return eventoMapper.toDto(evento);
    }

    public EventoDTO salvar(EventoDTO eventoDTO) {
//        condicoesParaExceptions(eventoDTO);
        Evento evento = eventoMapper.toEntity(eventoDTO);
        if (evento.getTipoInscricao() == null || false) {
            eventoRepositorio.save(evento);
            return eventoMapper.toDto(evento);
        }

        List<EventoPergunta> perguntas = evento.getPerguntas();

        evento.setPerguntas(new ArrayList<>());
        eventoRepositorio.save(evento);
        perguntas.forEach(pergunta -> pergunta.setEvento(evento));
        eventoPerguntaRepositorio.saveAll(perguntas);
        return eventoMapper.toDto(evento);
    }

    public EventoDTO editar(EventoDTO eventoDTO) {
        if(!eventoRepositorio.existsById(eventoDTO.getId())){
            throw new RegraNegocioException("Evento não existe na base de dados");
        }

        Evento eventopergunta = eventoRepositorio.findById(eventoDTO.getId()).orElseThrow(()
                -> new RegraNegocioException("Evento não encontrado"));

        List<Pergunta> listPergunta = new ArrayList<>();
        eventopergunta.getPerguntas().forEach(eventoPergunta -> listPergunta.add(perguntaRepositorio.
                findById(eventoPergunta.getPergunta().getId()).orElseThrow(
                () -> new RegraNegocioException("Pergunta não encontrado"))));

        Evento evento = eventoRepositorio.save(eventoMapper.toEntity(eventoDTO));
        List<InscricaoDTO> inscricaoDTO = inscricaoServico.buscarInscricaoPeloEventoId(eventoDTO.getId());
        List<UsuarioDTO> usuariosDtos = new ArrayList<>();

        if (eventoDTO.getDataFim().isBefore(eventoDTO.getDataInicio())
                || eventoDTO.getDataInicio().isBefore(LocalDateTime.now())){
            throw new RegraNegocioException("Data do evento inválida");
        }
        for (InscricaoDTO preInscricao: inscricaoDTO) {
            usuariosDtos.add(usuarioServico.obterUsuarioPorId(preInscricao.getIdUsuario()));
        }   

        return eventoMapper.toDto(evento);
    }

    private Evento obter(Integer id) {
        return eventoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("O id do Evento informado não foi encontrado"));
    }

    public void remover(Integer id) {
        // Se no DB nao existir este Evento lanca a Exception
        if (!eventoRepositorio.existsById(id)) {
            throw new RegraNegocioException("Evento com esse id não existe");
        }
        // Caso contrario ele vai buscar no DB das INscricao o Evento e tb remover de la
        inscricaoRepositorio.deleteAllByEvento(eventoRepositorio.findById(id).orElseThrow(()
                -> new RegraNegocioException("O evento não foi cadastrado")));
        eventoRepositorio.deleteById(id);
    }

    public void enviarEmail(Usuario usuario) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Cadastro de usuário");
        emailDTO.setCorpo("Você foi cadastrado com sucesso na plataforma de eventos, esta é sua chave de inscrição em eventos: <b>" + usuario.getChave() + "</b>");
        emailDTO.setDestinatario(usuario.getEmail());
        emailServico.sendMail(emailDTO);
    }

}