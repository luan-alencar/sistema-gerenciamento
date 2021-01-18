package com.basis.sge.servico;

import com.basis.sge.dominio.Inscricao;
import com.basis.sge.repositorio.InscricaoRepositorio;
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

    private final InscricaoRepositorio inscricaoRepositorio;
    private final PreInscricaoMapper preInscricaoMapper;

    // buscar todos
    public List<PreInscricaoDTO> listar() {
        List<Inscricao> inscricaoLista = inscricaoRepositorio.findAll();
        return preInscricaoMapper.toDto(inscricaoLista);
    }

    public PreInscricaoDTO obterPreInscricaoPorId(Integer id) {
        Inscricao inscricao = inscricaoRepositorio.findById(id).get();
        return preInscricaoMapper.toDto(inscricao);
    }

    public void deletar(Integer id) {
        inscricaoRepositorio.deleteById(id);
    }

    public PreInscricaoDTO atualizar(PreInscricaoDTO preInscricaoDTO) {
        Inscricao inscricaoAtualizado = preInscricaoMapper.toEntity(preInscricaoDTO);
        inscricaoRepositorio.save(inscricaoAtualizado);
        return preInscricaoMapper.toDto(inscricaoAtualizado);
    }

    public PreInscricaoDTO salvar(PreInscricaoDTO preInscricaoDTO) {
        Inscricao inscricao = inscricaoRepositorio.findById(preInscricaoDTO.getId()).get();
        if (inscricao != null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        inscricaoRepositorio.save(inscricao);
        return preInscricaoMapper.toDto(inscricao);
    }
}
