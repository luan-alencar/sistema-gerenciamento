package com.basis.sge.builder;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.Inscricao;
import com.basis.sge.dominio.InscricaoResposta;
import com.basis.sge.dominio.TipoSituacao;
import com.basis.sge.repositorio.TipoSituacaoRepositorio;
import com.basis.sge.servico.InscricaoServico;
import com.basis.sge.servico.dto.InscricaoDTO;
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

    @Autowired
    private TipoSituacaoRepositorio tipoSituacaoRepositorio;

    @Override
    public Inscricao construirEntidade() throws ParseException {

        Inscricao inscricao = new Inscricao();
        Evento evento = eventoBuilder.construir();
        TipoSituacao tipoSituacao = new TipoSituacao();
        tipoSituacao.setId(1);
        tipoSituacaoRepositorio.save(tipoSituacao);
        List<InscricaoResposta> respostaList = new ArrayList<>();
        InscricaoResposta resposta = new InscricaoResposta();

        inscricao.setEvento(evento);
        inscricao.setUsuario(usuarioBuilder.construir());
        inscricao.setIdTipoSituacao(tipoSituacao);

        resposta.setResposta("resposta teste");
        respostaList.add(resposta);

        inscricao.setResposta(respostaList);

        return inscricao;
    }

    @Override
    protected Inscricao persistir(Inscricao entidade) {
        InscricaoDTO inscricaoDTO = inscricaoServico.salvar(inscricaoMapper.toDto(entidade));
        return inscricaoMapper.toEntity(inscricaoDTO);
    }

    @Override
    protected Collection<Inscricao> obterTodos() {
        return inscricaoMapper.toEntity(inscricaoServico.listar());
    }

    @Override
    protected Inscricao obterPorId(Integer id) {
        return inscricaoMapper.toEntity(inscricaoServico.obterInscricaoPorId(id));
    }
}