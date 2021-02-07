import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Evento } from 'src/app/dominios/evento';
import { EventoPergunta } from 'src/app/dominios/evento-pergunta';
import { Inscricao } from 'src/app/dominios/inscricao';
import { InscricaoResposta } from 'src/app/dominios/inscricao-resposta';
import { Pergunta } from 'src/app/dominios/pergunta';
import { TipoEvento } from 'src/app/dominios/tipo-evento';
import { Usuario } from 'src/app/dominios/usuario';
import { EventoService } from 'src/app/modulos/evento/services/evento.service';
import { PerguntasService } from 'src/app/modulos/perguntas/services/perguntas.service';
import { InscricaoService } from '../../services/inscricao.service';

@Component({
  selector: 'app-inscricao-formulario',
  templateUrl: './inscricao-formulario.component.html',
  styleUrls: ['./inscricao-formulario.component.css']
})
export class InscricaoFormularioComponent implements OnInit {

  formInscricao: FormGroup;


  @Input() inscricao = new Inscricao();
  @Output() inscricaoSalva = new EventEmitter<Inscricao>();
  @Input() edicao = false;
  @Input('idEvento') set idEvento(idEvento: number) {
    if (idEvento) {
      this.buscarEvento(idEvento);
    }
  }
  inscricaoResposta = new InscricaoResposta();
  usuario = new Usuario();
  pergunta = new Pergunta();
  evento = new Evento();

  respostasInscricao: InscricaoResposta[] = [];
  perguntasEvento: Pergunta[] = [];

  resposta: string;
  inscricaoRespostas: InscricaoResposta;
  eventoPergunta: EventoPergunta;
  tipoSituacao: TipoEvento;

  constructor(
    private fb: FormBuilder,
    private inscricaoService: InscricaoService,
    private eventoService: EventoService,
    private perguntaService: PerguntasService,
    private route: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.buscarEvento(params.id);
    });

    this.formInscricao = this.fb.group({
      idUsuario: '',
      idEvento: '',
      idTipoSituacao: '',
      resposta: ''
    });
  }

  salvarResposta() {
    this.usuario = JSON.parse(window.localStorage.getItem("usuario"));
    let condicao = true;

    this.perguntasEvento.forEach(pergunta => {
      if (!pergunta.resposta && pergunta.obrigatoriedade) {
        condicao = false;
        alert("Você não respondeu uma pergunta obrigatoria")
        location.reload();

      }

      this.inscricaoResposta = new InscricaoResposta();
      this.inscricaoResposta.idEvento = this.evento.id;
      this.inscricaoResposta.idPergunta = pergunta.id;
      this.inscricaoResposta.idInscricao = null;
      this.inscricaoResposta.resposta = pergunta.resposta;

      this.respostasInscricao.push(this.inscricaoResposta)
    });

    if (condicao) {

      this.inscricao.idEvento = this.evento.id;
      this.inscricao.idUsuario = this.usuario.id;
      this.inscricao.idTipoSituacao = 1;
      this.respostasInscricao.forEach(resposta => {
        this.inscricao.resposta.push(resposta)
      });

      this.inscricaoService.salvarInscricao(this.inscricao).subscribe(
        () => {
          alert('Inscrição salva');
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        })
    }
  }


  buscarEvento(id: number) {

    this.eventoService.encontrarEventoPorId(id).subscribe((evento: Evento) => {
      this.evento = evento;
      console.log(evento);
      this.evento.perguntas.forEach(eventoPergunta => {
        this.buscarTodasPerguntasDoEvento(eventoPergunta.idPergunta);
      });
    });
  }

  buscarTodasPerguntasDoEvento(idPergunta: number) {
    this.perguntaService.buscarPergunta(idPergunta).subscribe((pergunta: Pergunta) => {
      this.perguntasEvento.push(pergunta);
      console.log(this.perguntasEvento);
    });
  }
}