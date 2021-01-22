package com.basis.sge.recurso;

import com.basis.sge.builder.PerguntaBuilder;
import com.basis.sge.dominio.Pergunta;
import com.basis.sge.repositorio.PerguntaRepositorio;
import com.basis.sge.servico.mapper.PerguntaMapper;
import com.basis.sge.util.IntTestComum;
import com.basis.sge.util.TestUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
public class PerguntaRecursoIT extends IntTestComum {

    @Autowired
    private PerguntaRepositorio perguntaRepositorio;

    @Autowired
    private PerguntaBuilder perguntaBuilder;

    @Autowired
    private PerguntaMapper perguntaMapper;

    @BeforeEach
    public void inicializar(){
        perguntaRepositorio.deleteAll();
    }

    @Test
    public void listarTest() throws Exception {

        perguntaBuilder.construir();

        getMockMvc().perform(get("/api/perguntas"))
                .andExpect(status().isOk());

    }

    @Test
    public void listarNullTest() throws Exception {
        getMockMvc().perform(get("/api/perguntas"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarTest() throws Exception {

        Pergunta pergunta = perguntaBuilder.construirEntidade();

        getMockMvc().perform(post("/api/perguntas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isCreated());

        Assert.assertEquals(1, perguntaRepositorio.findAll().size());

    }

    @Test
    public void salvarTituloNullTest() throws Exception {
        Pergunta pergunta = perguntaBuilder.construirEntidade();
        pergunta.setTitulo(null);

        getMockMvc().perform(post("/api/perguntas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarObrigatoriedadeNullTest() throws Exception {
        Pergunta pergunta = perguntaBuilder.construirEntidade();
        pergunta.setObrigatoriedade(null);

        getMockMvc().perform(post("/api/perguntas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void editarTest() throws Exception {

        Pergunta pergunta = perguntaBuilder.construir();
        pergunta.setTitulo("Alterando pergunta");

        getMockMvc().perform(put( "/api/perguntas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isOk());
    }

    @Test
    public void removerTest() throws Exception {
        Pergunta pergunta = perguntaBuilder.construir();

        getMockMvc().perform(delete("/api/perguntas/" + pergunta.getId()))
                .andExpect(status().isOk());

    }

    @Test
    public void removerIdInvalido() throws Exception {
        perguntaBuilder.construir();

        getMockMvc().perform(delete("/api/perguntas/2"))
                .andExpect(status().isBadRequest());
    }

}
