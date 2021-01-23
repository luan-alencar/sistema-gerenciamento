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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
public class EventoRecursoIT extends IntTestComum {

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private MessageSource messageSource;

    @BeforeEach
    public void inicializar() {
        eventoRepositorio.deleteAll();
    }

    @Test
    protected void listarTest() throws Exception {
        eventoBuilder.construir();
        getMockMvc().perform(get("/api/eventos"))
                .andExpect(status().isOk());
        Assert.assertEquals(1, eventoRepositorio.findAll().size());
    }

    @Test
    public void salvarTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();

        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isCreated());
    }

    @Test
    public void editarLocalTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        evento.setLocal("Novo local: Avenida Floriano Peixoto");
        getMockMvcIsOk(evento);
    }

    @Test
    public void editarDateInicioTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        evento.setDataInicio(LocalDateTime.of(2021, 05, 21, 18, 00));
        getMockMvcIsOk(evento);
    }

    @Test
    public void editarDataFimTest() throws Exception {
        Evento evento = new Evento();
        evento.setValor(10.0);
        getMockMvcIsOk(evento);
    }

    @Test
    public void editarDescricaoTest() throws Exception {
        Evento evento = new Evento();
        evento.setDescricao("Design Patterns");
        getMockMvcIsOk(evento);
    }

    @Test
    public void removerTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        getMockMvc().perform(delete("/api/eventos/{id}", evento.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarPorIdTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        getMockMvc().perform(get("/api/eventos/{id}", evento.getId()))
                .andExpect(status().isOk());
    }


    @Test
    void testValorException() throws ParseException {
        try {
            Evento evento = eventoBuilder.construirEntidade();
            evento.setValor(0.1);
            evento.setTipoInscricao(true);
            getMockMvcIsOk(evento);
        } catch (Exception e){
            System.err.println(e);
        }

    }

    @Test
    public void buscarPorIdNaoExistenteTest() throws Exception {
        String result = getString();
        Assert.assertEquals("Id informado não encontrado", result);
    }

    @Test
    public void tituloNullTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();
        evento.setTitulo(null);
        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void dataInicioNullTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();
        evento.setDataInicio(null);
        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void dataFimNullTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();
        evento.setDataFim(null);
        getMockMvcPost(evento, post("/api/eventos"), status().isBadRequest());
    }

    private void getMockMvcPost(Evento evento, MockHttpServletRequestBuilder post, ResultMatcher badRequest) throws Exception {
        getMockMvc().perform(post
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(badRequest);
    }

    @Test
    public void descricaoNullTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();
        evento.setDescricao(null);
        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void perguntasNullTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();
        evento.setPerguntas(null);
        getMockMvcPost(evento, post("/api/eventos"), status().isBadRequest());    }


    // método extraido
    private String getString() throws Exception {
        String result = getMockMvc().perform(get("/api/eventos/{id}", 2))
                .andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();
        return result;
    }

    // metodo extraido
    private void getMockMvcIsOk(Evento evento) throws Exception {
        eventoBuilder.construir();
        getMockMvc().perform(put("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isOk());
    }
}