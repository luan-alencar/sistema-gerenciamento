package com.basis.sge.recurso;

import com.basis.sge.builder.EventoBuilder;
import com.basis.sge.dominio.Evento;
import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.servico.mapper.EventoMapper;
import com.basis.sge.util.IntTestComum;
import com.basis.sge.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @BeforeEach
    public void inicializar() {
        eventoRepositorio.deleteAll();
    }

    @Test
    public void listarTest() throws Exception {
        eventoBuilder.construir();
        getMockMvc().perform(get("/api/eventos"))
                .andExpect(status().isOk());
//        .andExpect(assertEquals( 4, eventoRepositorio.findAll().size()));
    }

    @Test
    public void salvarTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();

        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isCreated());
    }

//    @Test
//    public void editarTest() throws Exception {
//
//        Usuario usuario = usuarioBuilder.construir();
//        usuario.setNome("Alterando usuario");
//
//        getMockMvc().perform(put( "/api/usuarios")
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
//                .andExpect(status().isOk());
//    }
    @Test
    public void editar() throws Exception {

        Evento evento = eventoBuilder.construir();
        evento.setDataInicio(LocalDateTime.of());
    }

}
