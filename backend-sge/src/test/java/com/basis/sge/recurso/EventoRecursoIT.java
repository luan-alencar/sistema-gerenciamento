package com.basis.sge.recurso;

import com.basis.sge.builder.EventoBuilder;
import com.basis.sge.servico.mapper.EventoMapper;
import com.basis.sge.util.IntTestComum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
public class EventoRecursoIT extends IntTestComum {

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private EventoMapper eventoMapper;

    @Test
    public void listarTest () throws Exception{
        eventoBuilder.construirEntidade();
        getMockMvc().perform(get("api/eventos"))
        .andExpect(status().isOk());
    }
}
