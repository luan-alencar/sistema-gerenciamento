package com.basis.sge.recurso;

import com.basis.sge.servico.PerguntaServico;
import com.basis.sge.servico.mapper.PerguntaMapper;
import com.basis.sge.util.IntTestComum;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
public class PerguntaRecursoIT extends IntTestComum {

    @Autowired
    private PerguntaServico perguntaServico;

    @Autowired
    private PerguntaMapper perguntaMapper;



}
