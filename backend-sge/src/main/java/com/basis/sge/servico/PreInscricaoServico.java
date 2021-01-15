package com.basis.sge.servico;

import com.basis.sge.dominio.PreInscricao;
import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.PreInscricaoRepository;
import com.basis.sge.servico.dto.UsuarioDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PreInscricaoServico {

    private final PreInscricaoRepository preInscricaoRepository;
    private final PreInscricaoMapper preInscricaoMapper;

    // buscar todos
    public List<PreInscricaoDTO> listar() {
        List<PreInscricao> preInscricaoLista = preInscricaoRepository.findAll();
        return preInscricaoMapper.toDto(preInscricaoLista);
    }

    public PreInscricaoDTO obterPreInscricaoPorId(Integer id) {
        PreInscricao preInscricao = preInscricaoRepository.findById(id).get();
        return preInscricaoMapper.toDto(preInscricao);
    }

    public void deletar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public PreInscricaoDTO atualizar(PreInscricaoDTO preInscricaoDTO) {
        PreInscricao preInscricaoAtualizado = preInscricaoMapper.toEntity(preInscricaoDTO);
        preInscricaoRepository.save(preInscricaoAtualizado);
        return preInscricaoMapper.toDto(preInscricaoAtualizado);
    }

    public PreInscricaoDTO salvar(PreInscricaoDTO preInscricaoDTO) {
        preInscricaoRepository.save(usuario);
        return preInscricaoMapper.toDto(usuario);
    }}
