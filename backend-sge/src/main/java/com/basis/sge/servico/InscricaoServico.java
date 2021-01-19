package com.basis.sge.servico;

import com.basis.sge.dominio.Inscricao;
import com.basis.sge.repositorio.InscricaoRepositorio;
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


    private final InscricaoRepositorio preInscricaoRepository;
    private final InscricaoMapper inscricaoMapper;

    // buscar todos
    public List<InscricaoDTO> listar() {
        List<Inscricao> inscricaoLista = preInscricaoRepository.findAll();
        return inscricaoMapper.toDto(inscricaoLista);
    }

    public InscricaoDTO obterPreInscricaoPorId(Integer id) {
        Inscricao inscricao = preInscricaoRepository.findById(id).get();
        return inscricaoMapper.toDto(inscricao);
    }

    public void deletar(Integer id) {
        preInscricaoRepository.deleteById(id);
    }

    public InscricaoDTO atualizar(InscricaoDTO inscricaoDTO) {
        Inscricao inscricaoAtualizado = inscricaoMapper.toEntity(inscricaoDTO);
        preInscricaoRepository.save(inscricaoAtualizado);
        return inscricaoMapper.toDto(inscricaoAtualizado);
    }

    public InscricaoDTO salvar(InscricaoDTO inscricaoDTO) {
        Inscricao inscricao = preInscricaoRepository.findById(inscricaoDTO.getId()).get();
        if (inscricao != null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        preInscricaoRepository.save(inscricao);
        return inscricaoMapper.toDto(inscricao);
    }
}
