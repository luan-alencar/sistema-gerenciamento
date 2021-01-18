package com.basis.sge.servico;

import com.basis.sge.dominio.PreInscricao;
import com.basis.sge.repositorio.InscricaoRepository;
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

    private final InscricaoRepository inscricaoRepository;
    private final InscricaoMapper inscricaoMapper;

    // buscar todos
    public List<InscricaoDTO> listar() {
        List<PreInscricao> preInscricaoLista = inscricaoRepository.findAll();
        return inscricaoMapper.toDto(preInscricaoLista);
    }

    public InscricaoDTO obterPreInscricaoPorId(Integer id) {
        PreInscricao preInscricao = inscricaoRepository.findById(id).get();
        return inscricaoMapper.toDto(preInscricao);
    }

    public void deletar(Integer id) {
        inscricaoRepository.deleteById(id);
    }

    public InscricaoDTO atualizar(InscricaoDTO inscricaoDTO) {
        PreInscricao preInscricaoAtualizado = inscricaoMapper.toEntity(inscricaoDTO);
        inscricaoRepository.save(preInscricaoAtualizado);
        return inscricaoMapper.toDto(preInscricaoAtualizado);
    }

    public InscricaoDTO salvar(InscricaoDTO inscricaoDTO) {
        PreInscricao preInscricao = inscricaoRepository.findById(inscricaoDTO.getId()).get();
        if (preInscricao != null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        inscricaoRepository.save(preInscricao);
        return inscricaoMapper.toDto(preInscricao);
    }
}
