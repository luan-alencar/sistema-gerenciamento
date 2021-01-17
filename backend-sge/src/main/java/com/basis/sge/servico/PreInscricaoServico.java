package com.basis.sge.servico;

import com.basis.sge.dominio.PreInscricao;
import com.basis.sge.repositorio.PreInscricaoRepositorio;
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
public class PreInscricaoServico {

    private final PreInscricaoRepositorio preInscricaoRepositorio;
    private final PreInscricaoMapper preInscricaoMapper;

    // buscar todos
    public List<PreInscricaoDTO> listar() {
        List<PreInscricao> preInscricaoLista = preInscricaoRepositorio.findAll();
        return preInscricaoMapper.toDto(preInscricaoLista);
    }

    public PreInscricaoDTO obterPreInscricaoPorId(Integer id) {
        PreInscricao preInscricao = preInscricaoRepositorio.findById(id).get();
        return preInscricaoMapper.toDto(preInscricao);
    }

    public void remover(Integer id) {
        preInscricaoRepositorio.deleteById(id);
    }

    public PreInscricaoDTO editar(PreInscricaoDTO preInscricaoDTO) {
        PreInscricao preInscricaoAtualizado = preInscricaoMapper.toEntity(preInscricaoDTO);
        preInscricaoRepositorio.save(preInscricaoAtualizado);
        return preInscricaoMapper.toDto(preInscricaoAtualizado);
    }

    public PreInscricaoDTO salvar(PreInscricaoDTO preInscricaoDTO) {
        PreInscricao preInscricao = preInscricaoRepositorio.findById(preInscricaoDTO.getId()).get();
        if (preInscricao != null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        preInscricaoRepositorio.save(preInscricao);
        return preInscricaoMapper.toDto(preInscricao);
    }
}
