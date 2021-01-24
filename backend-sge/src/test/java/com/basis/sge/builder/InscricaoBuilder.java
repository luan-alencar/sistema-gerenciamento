package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.Inscricao;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Collection;

@RunWith(SpringRunner.class)
@Transactional
public class InscricaoBuilder extends ConstrutorDeEntidade<Inscricao> {

    @Override
    protected Inscricao construirEntidade() throws ParseException {

        Inscricao inscricao = new Inscricao();

        return null;
    }

    @Override
    protected Inscricao persistir(Inscricao entidade) {
        return null;
    }

    @Override
    protected Evento persistir(Evento evento) {
        return null;
    }

    @Override
    protected Collection<Inscricao> obterTodos() {
        return null;
    }

    @Override
    protected Inscricao obterPorId(Integer id) {
        return null;
    }
}
