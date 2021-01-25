package com.basis.sge.servico;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.Inscricao;
import com.basis.sge.dominio.InscricaoResposta;
import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.repositorio.InscricaoRepositorio;
import com.basis.sge.repositorio.InscricaoRespostaRepositorio;
import com.basis.sge.servico.dto.InscricaoDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.InscricaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InscricaoServico {

    private final InscricaoRepositorio inscricaoRepositorio;
    private final InscricaoMapper inscricaoMapper;
    private final EventoRepositorio eventoRepositorio;
    private final InscricaoRespostaRepositorio inscricaoRespostaRepositorio;

    // buscar todos
    public List<InscricaoDTO> listar() {
        if (inscricaoRepositorio.findAll().size() == 0) {
            throw new RegraNegocioException("Sem inscrições até o momento.");
        }
        List<Inscricao> inscricoes = inscricaoRepositorio.findAll();
        return inscricaoMapper.toDto(inscricoes);
    }

    public InscricaoDTO obterInscricaoPorId(Integer id) {
        Inscricao inscricao = inscricaoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return inscricaoMapper.toDto(inscricao);
    }

    // método privado para servir somente como utilidade para encontrar um elemento e retornar uma exceçao caso não encontre
    private Inscricao obterIdUtil(Integer id) {
        return inscricaoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("O id da inscrição informado não foi encontrado!"));
    }

    public void deletar(Integer id) {
        Inscricao inscricao = obterIdUtil(id);
        inscricaoRepositorio.delete(inscricao);
    }

    public InscricaoDTO atualizar(InscricaoDTO inscricaoDTO) {
        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);
        inscricaoRepositorio.save(inscricao);
        return inscricaoMapper.toDto(inscricao);
    }

    public InscricaoDTO salvar(InscricaoDTO inscricaoDTO) {
        // como InscricaoMapper usa a interface InscricaoRespostaMapper apenas isso basta, pois, o mapper já faz o trabalho
//        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);
//        inscricaoRepositorio.save(inscricao);
//        return inscricaoMapper.toDto(inscricao);

        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);
        List<InscricaoResposta> respostas = inscricao.getResposta();

        Evento evento = eventoRepositorio.findById(inscricao.getIdEvento().getId())
                .orElseThrow(() -> new RegraNegocioException("Id do evento incorreto"));

        inscricao.setResposta(inscricao.getResposta());

        inscricaoRepositorio.save(inscricao);

        respostas.forEach(resposta -> {
            resposta.setInscricao(inscricao);
            resposta.setEvento(evento);
        });

        inscricaoRespostaRepositorio.saveAll(respostas);

        return inscricaoMapper.toDto(inscricao);
    }
}