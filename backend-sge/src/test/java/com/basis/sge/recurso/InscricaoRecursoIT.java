package com.basis.sge.recurso;

import com.basis.sge.builder.InscricaoBuilder;
import com.basis.sge.dominio.Inscricao;
import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.repositorio.InscricaoRepositorio;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.mapper.InscricaoMapper;
import com.basis.sge.util.IntTestComum;
import com.basis.sge.util.TestUtil;
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
public class InscricaoRecursoIT extends IntTestComum {

    @Autowired
    private InscricaoBuilder inscricaoBuilder;

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @Autowired
    private InscricaoMapper inscricaoMapper;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @BeforeEach
    public void inicializar(){
        inscricaoRepositorio.deleteAll();
        usuarioRepositorio.deleteAll();
        eventoRepositorio.deleteAll();
    }

    @Test
    public void listarTest() throws Exception {
        inscricaoBuilder.construir();

        getMockMvc().perform(get("/api/inscricoes"))
                .andExpect(status().isOk());
    }

    @Test
    public void listarNullTest() throws Exception {
        getMockMvc().perform(get("/api/inscricoes"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void obterInscricaoPorIdTest() throws Exception {
        Inscricao inscricao = inscricaoBuilder.construir();

        getMockMvc().perform(get("/api/inscricoes/{id}", inscricao.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void obterInscricaoPorIdInvalidoTest() throws Exception {
        inscricaoBuilder.construir();

        getMockMvc().perform(get("/api/inscricoes/{id}", 2))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarTest() throws Exception {
        Inscricao inscricao = inscricaoBuilder.construirEntidade();

        getMockMvc().perform(post("/api/inscricoes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(inscricaoMapper.toDto(inscricao))))
                .andExpect(status().isCreated());

        //Assert.assertEquals(1, inscricaoRepositorio.findAll().size());

    }

    @Test
    public void editarTest() throws Exception {
        Inscricao inscricao = inscricaoBuilder.construir();

        getMockMvc().perform(put( "/api/inscricoes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(inscricaoMapper.toDto(inscricao))))
                .andExpect(status().isOk());
    }

    @Test
    public void removerTest() throws Exception {
        Inscricao inscricao = inscricaoBuilder.construir();

        getMockMvc().perform(delete("/api/inscricoes/" + inscricao.getId()))
                .andExpect(status().isOk());

    }

    @Test
    public void removerIdInvalidoTest() throws Exception {
        inscricaoBuilder.construir();

        getMockMvc().perform(delete( "/api/inscricoes/1"))
                .andExpect(status().isBadRequest());

    }

}