import { EventoFormularioComponent } from './../../../evento/components/evento-formulario/evento-formulario.component';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventoPergunta } from 'src/app/dominios/evento-pergunta';
import { Inscricao } from 'src/app/dominios/inscricao';
import { InscricaoResposta } from 'src/app/dominios/inscricao-resposta';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntasService } from 'src/app/modulos/perguntas/services/perguntas.service';
import { InscricaoService } from '../../services/inscricao.service';
import { TipoSituacao } from './../../../../dominios/tipo-situacao';
import { Usuario } from 'src/app/dominios/usuario';

@Component({
  selector: 'app-inscricao-formulario',
  templateUrl: './inscricao-formulario.component.html',
  styleUrls: ['./inscricao-formulario.component.css']
})
export class InscricaoFormularioComponent implements OnInit {

  respostas: InscricaoResposta[] = [];
  perguntasEvento: Pergunta[] = [];
  usuario = new Usuario();
  inscricao = new Inscricao();
  pergunta: Pergunta;
  eventoPergunta: EventoPergunta;
  tipoSituacao: TipoSituacao;

  constructor(
    private route: ActivatedRoute,
    private inscricaoService: InscricaoService,
    private perguntaService: PerguntasService,
    private eventoForm: EventoFormularioComponent
  ) { }

  ngOnInit(): void {
    this.buscarTodasPerguntas();
  }

  private buscarTodasPerguntas() {
    this.eventoForm.buscarPerguntas();
  }

  salvar() {

    // Inscricao inscricao = inscricaoMapper.toEntity(inscricaoDTO);
    //     List<InscricaoResposta> respostas = inscricao.getResposta();

    //     inscricao.setResposta(new ArrayList<>());

    //     inscricaoRepositorio.save(inscricao);

    //     respostas.forEach(resposta -> {
    //         resposta.setInscricao(inscricao);
    //     });

    //     inscricaoRespostaRepositorio.saveAll(respostas);

    //     return inscricaoMapper.toDto(inscricao);
    this.inscricao.resposta.forEach(resp => {
      this.respostas.push(resp);
    });
  }
}
