package com.basis.sge.builder;

import com.basis.sge.dominio.Pergunta;
import com.basis.sge.servico.PerguntaServico;
import com.basis.sge.servico.dto.PerguntaDTO;
import com.basis.sge.servico.mapper.PerguntaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

@Component
public class PerguntaBuilder extends ConstrutorDeEntidade<Pergunta> {

    @Autowired
    private PerguntaServico perguntaServico;

    @Autowired
    private PerguntaMapper perguntaMapper;

    @Override
    public Pergunta construirEntidade() throws ParseException {

        Pergunta pergunta = new Pergunta();
        pergunta.setTitulo("titulo da pergunta");
        pergunta.setObrigatoriedade(false);

        return pergunta;
    }

    @Override
    protected Pergunta persistir(Pergunta entidade) {
        perguntaServico.salvar(perguntaMapper.toDto(entidade));
        return entidade;
    }

    @Override
    protected Collection<Pergunta> obterTodos() {
        List<PerguntaDTO> lista = perguntaServico.listar();
        List<Pergunta> perguntas = perguntaMapper.toEntity(lista);
        return perguntas;
    }

    @Override
    protected Pergunta obterPorId(Integer id) {
        return perguntaMapper.toEntity(perguntaServico.buscarPerguntaId(id));
    }
}