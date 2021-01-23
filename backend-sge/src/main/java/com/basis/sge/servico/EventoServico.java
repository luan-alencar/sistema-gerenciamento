package com.basis.sge.servico;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.EventoPergunta;
import com.basis.sge.repositorio.EventoPerguntaRepositorio;
import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.servico.dto.EventoDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServico {

    private final EventoRepositorio eventoRepositorio;
    private final EventoPerguntaRepositorio eventoPerguntaRepositorio;
    private final EventoMapper eventoMapper;

    public List<EventoDTO> listar() {
        List<Evento> lista = eventoRepositorio.findAll();
        return eventoMapper.toDto(lista);
    }

    public EventoDTO obterPorId(Integer id) {
        Evento evento = obterEventoPorId(id);
        return eventoMapper.toDto(evento);
    }

    private Evento obterEventoPorId(Integer id) {
        return eventoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Id informado não encontrado"));
    }

    public EventoDTO salvar(EventoDTO eventoDTO) {

        if (eventoDTO.getTipoInscricao() == null) {
            throw new RegraNegocioException("Escolha o tipo de inscrição para o evento");
        }

        if (eventoDTO.getTitulo() == null) {
            throw new RegraNegocioException("Campo de titulo é obrigatorio!");
        }

        if (eventoDTO.getDataInicio() == null) {
            throw new RegraNegocioException("O campo data de inicio e obrigatorio!");
        }

        if (eventoDTO.getDataFim() == null) {
            throw new RegraNegocioException("O campo data de fim e obrigatorio!");
        }

        if (eventoDTO.getDataInicio() == null) {
            throw new RegraNegocioException("Campo de titulo é obrigatorio!");
        }

        if (eventoDTO.getLocal() == null) {
            throw new RegraNegocioException("O campo local é obrigatorio!");
        }

        if (eventoDTO.getDescricao() == null) {
            throw new RegraNegocioException("A descrição do evento é obrigatória!");
        }

//        if (eventoDTO.getPerguntas() == null) {
//            throw new RegraNegocioException("Pelo menos 1 pergunta deve ser atribuida a um evento!");
//        }

//        if (eventoDTO.getTipoInscricao() == true && eventoDTO.getValor() < 0) {
//            throw new RegraNegocioException("O valor para ser cobrado tem que ser mais que 0!");
//        }

        Evento evento = eventoMapper.toEntity(eventoDTO);
        List<EventoPergunta> perguntas = new ArrayList<>();

        evento.setPerguntas(evento.getPerguntas());

        eventoRepositorio.save(evento);

        perguntas.forEach(pergunta -> {
            pergunta.setEvento(evento);
        });

        eventoPerguntaRepositorio.saveAll(perguntas);

        return eventoMapper.toDto(evento);
    }

    public EventoDTO editar(EventoDTO eventoDTO) {
        Evento evento = eventoMapper.toEntity(eventoDTO);
        eventoRepositorio.save(evento);
        return eventoMapper.toDto(evento);
    }

    public void remover(Integer id) {
        Evento evento = obterEventoPorId(id);
        eventoRepositorio.delete(evento);
    }
}
