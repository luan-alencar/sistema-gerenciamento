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
                .orElseThrow(() -> new RegraNegocioException("Inscrição não existe!"));
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

        //Validação de dados null - falta

        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);
        List<InscricaoResposta> respostas = inscricao.getResposta();

        inscricao.setResposta(new ArrayList<>());

        inscricaoRepositorio.save(inscricao);

        respostas.forEach(resposta -> {
            resposta.setInscricao(inscricao);
        });

        inscricaoRespostaRepositorio.saveAll(respostas);

        return inscricaoMapper.toDto(inscricao);
    }

    private void validarDadosNull(InscricaoDTO inscricaoDTO){
        if(inscricaoDTO.getIdEvento() == null){
            throw new RegraNegocioException("Id do evento precisa ser informado!");
        }
        if(inscricaoDTO.getIdUsuario() == null){
            throw new RegraNegocioException("Id do usuario precisa ser informado!");
        }
        if(inscricaoDTO.getIdTipoSituacao() == null){
            throw new RegraNegocioException("Id da situacao do usuario precisa ser informado!");
        }
        if(inscricaoDTO.getIdUsuario() == null){
            throw new RegraNegocioException("Id do usuario precisa ser informado!");
        }
    }
}