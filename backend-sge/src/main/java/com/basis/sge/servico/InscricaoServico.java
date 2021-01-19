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


    private final InscricaoRepositorio inscricaoRepositorio;
    private final InscricaoMapper inscricaoMapper;

    // buscar todos
    public List<InscricaoDTO> listar() {
        List<Inscricao> inscricaoLista = inscricaoRepositorio.findAll();
        return inscricaoMapper.toDto(inscricaoLista);
    }

    public InscricaoDTO obterPreInscricaoPorId(Integer id) {
        Inscricao inscricao = inscricaoRepositorio.findById(id).get();
        return inscricaoMapper.toDto(inscricao);
    }

    public void deletar(Integer id) {
        inscricaoRepositorio.deleteById(id);
    }

    public InscricaoDTO atualizar(InscricaoDTO inscricaoDTO) {
        Inscricao inscricaoAtualizado = inscricaoMapper.toEntity(inscricaoDTO);
        inscricaoRepositorio.save(inscricaoAtualizado);
        return inscricaoMapper.toDto(inscricaoAtualizado);
    }

    public InscricaoDTO salvar(InscricaoDTO inscricaoDTO) {
        Inscricao inscricao = inscricaoRepositorio.findById(inscricaoDTO.getId()).get();
        if (inscricao != null) {
            throw new RegraNegocioException("Usuario já existente!");
        }
        inscricaoRepositorio.save(inscricao);
        return inscricaoMapper.toDto(inscricao);
    }
}
