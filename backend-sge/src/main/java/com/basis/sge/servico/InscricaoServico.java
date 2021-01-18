package com.basis.sge.servico;

import com.basis.sge.dominio.Inscricao;
import com.basis.sge.repositorio.InscricaoRepository;
import com.basis.sge.servico.dto.PreInscricaoDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.PreInscricaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InscricaoServico {

    private final InscricaoRepository inscricaoRepository;
    private final PreInscricaoMapper preInscricaoMapper;

    // buscar todos
    public List<PreInscricaoDTO> listar() {
        List<Inscricao> inscricaoLista = inscricaoRepository.findAll();
        return preInscricaoMapper.toDto(inscricaoLista);
    }

    public PreInscricaoDTO obterPreInscricaoPorId(Integer id) {
        Inscricao inscricao = inscricaoRepository.findById(id).get();
        return preInscricaoMapper.toDto(inscricao);
    }

    public void deletar(Integer id) {
        inscricaoRepository.deleteById(id);
    }

    public PreInscricaoDTO atualizar(PreInscricaoDTO preInscricaoDTO) {
        Inscricao inscricaoAtualizado = preInscricaoMapper.toEntity(preInscricaoDTO);
        inscricaoRepository.save(inscricaoAtualizado);
        return preInscricaoMapper.toDto(inscricaoAtualizado);
    }

    public PreInscricaoDTO salvar(PreInscricaoDTO preInscricaoDTO) {
        Inscricao inscricao = inscricaoRepository.findById(preInscricaoDTO.getId()).get();
        if (inscricao != null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        inscricaoRepository.save(inscricao);
        return preInscricaoMapper.toDto(inscricao);
    }
}
