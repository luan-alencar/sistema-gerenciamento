package com.basis.sge.servico;


import com.basis.sge.dominio.Pergunta;
import com.basis.sge.repositorio.PerguntaRepositorio;
import com.basis.sge.servico.dto.PerguntaDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
import com.basis.sge.servico.mapper.PerguntaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PerguntaServico {

    private final PerguntaRepositorio perguntaRepositorio;
    private final PerguntaMapper perguntaMapper;


    public List<PerguntaDTO> listar() {

        List<Pergunta> listaPergunta = perguntaRepositorio.findAll(); //Busca no repositório todas as perguntas
        return perguntaMapper.toDto(listaPergunta); //Passa as perguntas para o dto e envia para os recursos uma listaDto

    }

    public PerguntaDTO buscarPerguntaId(Integer id) {

        Pergunta pergunta = perguntaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pergunta não encontrada"));

        return  perguntaMapper.toDto(pergunta);

    }

    public PerguntaDTO salvar(PerguntaDTO perguntaDTO) {

        Pergunta pergunta = perguntaMapper.toEntity(perguntaDTO);
        perguntaRepositorio.save(pergunta);

        return perguntaDTO;


    }

    public PerguntaDTO atualizar(PerguntaDTO perguntaDTO) {

        Pergunta pergunta = perguntaMapper.toEntity(perguntaDTO);
        perguntaRepositorio.save(pergunta);
        return perguntaDTO;

    }

    public void deletar(Integer id) {

        perguntaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pergunta não encontrada"));

        perguntaRepositorio.deleteById(id);


    }


}
