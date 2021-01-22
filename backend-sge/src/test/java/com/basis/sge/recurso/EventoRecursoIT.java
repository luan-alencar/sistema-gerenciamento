package com.basis.sge.recurso;

import com.basis.sge.builder.EventoBuilder;
import com.basis.sge.dominio.Evento;
import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.servico.mapper.EventoMapper;
import com.basis.sge.util.IntTestComum;
import com.basis.sge.util.TestUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
public class EventoRecursoIT extends IntTestComum {

    @Autowired(required = true)
    private EventoBuilder eventoBuilder;

    @Autowired(required = true)
    private EventoMapper eventoMapper;

    @Autowired(required = true)
    private EventoRepositorio eventoRepositorio;


    @BeforeEach
    public void inicializar() {
        eventoRepositorio.deleteAll();
    }

    @Test
    protected void listarTest() throws Exception {
        eventoBuilder.construir();
        getMockMvc().perform(get("/api/eventos"))
                .andExpect(status().isOk());
        Assert.assertEquals(0, eventoRepositorio.findAll().size());
    }

    @Test
    protected void salvarTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();

        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isCreated());
    }

    @Test
    public void editarTest() throws Exception {

        Evento evento = eventoBuilder.construir();
        evento.setLocal("Novo local: Avenida Floriano Peixoto");
        getMockMvc().perform(put("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isOk());
    }
}
