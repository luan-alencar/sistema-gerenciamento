package com.basis.sge.builder;

import com.basis.sge.dominio.*;
import com.basis.sge.servico.InscricaoServico;
import com.basis.sge.servico.mapper.InscricaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class InscricaoBuilder extends ConstrutorDeEntidade<Inscricao> {

    @Autowired
    private InscricaoServico inscricaoServico;

    @Autowired
    private InscricaoMapper inscricaoMapper;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private PerguntaBuilder perguntaBuilder;

    @Override
    public Inscricao construirEntidade() throws ParseException {

        TipoSituacao tipoSituacao = new TipoSituacao();
        tipoSituacao.setId(1);

        Usuario usuario = usuarioBuilder.construirEntidade();
        Evento evento = eventoBuilder.construirEntidade();
        Pergunta pergunta = perguntaBuilder.construirEntidade();

        List<InscricaoResposta> respostas = new ArrayList<>();
        respostas.forEach(resposta -> {
            resposta.setEvento(evento);
            resposta.setResposta("resposta teste");
            resposta.setPergunta(pergunta);
            resposta.setInscricao(null);
        });

        Inscricao inscricao = new Inscricao();
        inscricao.setIdUsuario(usuario);
        inscricao.setIdEvento(evento);
        inscricao.setIdTipoSituacao(tipoSituacao);
        inscricao.setResposta(respostas);

        return inscricao;
    }

    @Override
    protected Inscricao persistir(Inscricao entidade) {
        inscricaoServico.salvar(inscricaoMapper.toDto(entidade));
        return entidade;
    }

    @Override
    public Collection<Inscricao> obterTodos() {

        return inscricaoMapper.toEntity(inscricaoServico.listar());
    }

    @Override
    public Inscricao obterPorId(Integer id) {
        return inscricaoMapper.toEntity(inscricaoServico.obterInscricaoPorId(id));
    }
}