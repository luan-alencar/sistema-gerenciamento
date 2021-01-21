package com.basis.sge.builder;

import com.basis.sge.repositorio.EventoRepositorio;
import com.basis.sge.servico.mapper.EventoMapper;
import com.basis.sge.util.IntTestComum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
public class EventoIT extends IntTestComum {

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @BeforeEach
    public void inicializar(){
        eventoRepositorio.deleteAll();
    }

    public void listaTest() throws Exception{

    }
}
