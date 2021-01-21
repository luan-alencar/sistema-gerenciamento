package com.basis.sge.recurso;

import com.basis.sge.builder.UsuarioBuilder;
import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.mapper.UsuarioMapper;
import com.basis.sge.util.IntTestComum;
import com.basis.sge.util.TestUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
public class UsuarioRecursoIT extends IntTestComum {

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @BeforeEach
    public void inicializar(){
        //limpar banco
        usuarioRepositorio.deleteAll();
    }

    @Test
    public void listarTest() throws Exception {
        usuarioBuilder.construir();

        getMockMvc().perform(get("/api/usuarios"))
            .andExpect(status().isOk());
    }

    @Test
    public void listarNullTest() throws Exception {
        getMockMvc().perform(get("/api/usuarios"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarTest() throws Exception {
        Usuario usuario = usuarioBuilder.construirEntidade();

        getMockMvc().perform(post("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isCreated());

        Assert.assertEquals(1, usuarioRepositorio.findAll().size());

    }

    @Test
    public void salvarCpfRepetidoTest() throws Exception {
        usuarioBuilder.construir();
        Usuario usuario = usuarioBuilder.construirEntidade();
        usuario.setEmail("teste2@gmail.com");

        getMockMvc().perform(post("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void salvarEmailRepetidoTest() throws Exception {
        usuarioBuilder.construir();
        Usuario usuario = usuarioBuilder.construirEntidade();
        usuario.setCpf("85236974123");

        getMockMvc().perform(post("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void dataNascimentoMenorQueLimiteInferiorTest() throws Exception {
        Usuario usuario = usuarioBuilder.construirEntidade();
        usuario.setDataNascimento(LocalDate.of(1900,5,5));

        getMockMvc().perform(post("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void dataNascimentoMaiorQueLimiteSuperiorTest() throws Exception {
        Usuario usuario = usuarioBuilder.construirEntidade();
        usuario.setDataNascimento(LocalDate.now());

        getMockMvc().perform(post("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void editarTest() throws Exception {

        Usuario usuario = usuarioBuilder.construir();
        usuario.setNome("Alterando usuario");

        getMockMvc().perform(put( "/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isOk());
    }

    @Test
    public void removerTest() throws Exception {
        Usuario usuario = usuarioBuilder.construir();

        getMockMvc().perform(delete( "/api/usuarios/"+usuario.getId()))
                .andExpect(status().isOk());

    }

}
