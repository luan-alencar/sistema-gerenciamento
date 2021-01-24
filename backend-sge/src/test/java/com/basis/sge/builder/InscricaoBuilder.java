package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.Inscricao;
import com.basis.sge.dominio.TipoSituacao;
import com.basis.sge.dominio.Usuario;
import liquibase.pro.packaged.T;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Collection;

@RunWith(SpringRunner.class)
@Transactional
public class InscricaoBuilder extends ConstrutorDeEntidade<Inscricao> {

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Override
    protected Inscricao construirEntidade() throws ParseException {

        Inscricao inscricao = new Inscricao();
        Evento evento = eventoBuilder.construirEntidade();
        TipoSituacao tipoSituacao = new TipoSituacao();
        Usuario usuario = usuarioBuilder.construirEntidade();

        inscricao.setIdEvento(evento);
        inscricao.setIdTipoSituacao(tipoSituacao);
        inscricao.setIdUsuario(usuario);

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
