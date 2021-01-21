package com.basis.sge.util;

import com.basis.sge.recurso.UsuarioRecurso;
import com.basis.sge.repositorio.UsuarioRepositorio;
import com.basis.sge.servico.UsuarioServico;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuickTest {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    public void contextRun() throws Exception {
        assertThat(usuarioRepositorio).isNull();
    }
}
