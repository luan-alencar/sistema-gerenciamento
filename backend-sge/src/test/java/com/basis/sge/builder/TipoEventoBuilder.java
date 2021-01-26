package com.basis.sge.builder;

import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.repositorio.TipoEventoRepositorio;
import com.basis.sge.servico.TipoEventoServico;
import com.basis.sge.servico.mapper.TipoEventoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;

@Component
public class TipoEventoBuilder extends ConstrutorDeEntidade<TipoEvento> {

    @Autowired
    private TipoEventoServico tipoEventoServico;

    @Autowired
    private TipoEventoRepositorio tipoEventoRepositorio;

    @Autowired
    private TipoEventoMapper tipoEventoMapper;

    @Override
    protected TipoEvento construirEntidade() throws ParseException {

        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setDescricao("Workshop");
        return tipoEvento;
    }

    @Override
    protected TipoEvento persistir(TipoEvento entidade) {
        tipoEventoServico.salvar(tipoEventoMapper.toDto(entidade));
        return entidade;
    }

    @Override
    protected Collection<TipoEvento> obterTodos() {
        return null;
    }

    @Override
    protected TipoEvento obterPorId(Integer id) {
        return null;
    }
}
