package com.basis.sge.servico;


import com.basis.sge.dominio.Pergunta;
import com.basis.sge.repositorio.PerguntaRepositorio;
import com.basis.sge.servico.mapper.PerguntaMapper;
import com.basis.sge.servico.dto.PerguntaDTO;
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


    public List<PerguntaDTO> listar(){

        List<Pergunta> listaPergunta = perguntaRepositorio.findAll(); //Busca no repositório todas as perguntas
        return perguntaMapper.toDto(listaPergunta); //Passa as perguntas para o dto e envia para os recursos uma listaDto

    }

    public PerguntaDTO buscarPerguntaId(Integer id){

        Pergunta pergunta = perguntaRepositorio.findById(id).get();     //  get () só pode retornar um valor se o objeto empacotado
        return perguntaMapper.toDto(pergunta);                    // não for nulo, caso contrário, ele lança uma exceção de nenhum elemento:

    }

    public PerguntaDTO salvar (PerguntaDTO perguntaDTO){

        Pergunta pergunta = perguntaMapper.toEntity(perguntaDTO);
        perguntaRepositorio.save(pergunta);

        return perguntaDTO;


    }
    public  PerguntaDTO atualizar(PerguntaDTO perguntaDTO){

        Pergunta pergunta = perguntaMapper.toEntity(perguntaDTO);
        perguntaRepositorio.save(pergunta);
        return perguntaDTO;

    }

    public void deletar (Integer id){

        perguntaRepositorio.deleteById(id);


    }






}
