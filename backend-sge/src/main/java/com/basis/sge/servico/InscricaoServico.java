package com.basis.sge.servico;

import com.basis.sge.dominio.*;
import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.repositorio.InscricaoRepositorio;
import com.basis.sge.repositorio.InscricaoRespostaRepositorio;
import com.basis.sge.servico.dto.InscricaoDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.InscricaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InscricaoServico {

    private final InscricaoRespostaRepositorio inscricaoRespostaRepositorio;
    private final InscricaoRepositorio inscricaoRepositorio;
    private final InscricaoMapper inscricaoMapper;
    private final EventoRepositorio eventoRepositorio;

    // buscar todos
    public List<InscricaoDTO> listar() {
        List<Inscricao> inscricoes = inscricaoRepositorio.findAll();
        if(inscricoes.isEmpty()){
            throw new RegraNegocioException("Nenhum inscrição cadastrada!");
        }
        return inscricaoMapper.toDto(inscricoes);
    }

    public InscricaoDTO obterInscricaoPorId(Integer id) {
        Inscricao inscricao = inscricaoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuario não existe!"));
        return inscricaoMapper.toDto(inscricao);
    }

    public void deletar(Integer id) {
        inscricaoRepositorio.delete(inscricaoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Id informado não encontrado")));
    }

    public InscricaoDTO atualizar(InscricaoDTO inscricaoDTO) {
        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);
        inscricaoRepositorio.save(inscricao);
        return inscricaoMapper.toDto(inscricao);
    }

    public InscricaoDTO salvar(InscricaoDTO inscricaoDTO) {

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