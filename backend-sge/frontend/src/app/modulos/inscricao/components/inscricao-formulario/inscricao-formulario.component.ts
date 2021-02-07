import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
  @Input('idEvento') set idEventofn(idEvento: number) {
    if (idEvento) {
      this.buscarEvento(idEvento);
    }
  }
  inscricaoResposta = new InscricaoResposta();
  inscricaoRespostas: InscricaoResposta[] = [];
  usuario = new Usuario();
  pergunta = new Pergunta();
  perguntas: Pergunta[] = [];
  evento = new Evento();
  perguntasEvento: Pergunta[] = [];
  resposta: string;
  eventoPergunta: EventoPergunta;
  tipoSituacao: TipoEvento;

  constructor(
    private fb: FormBuilder,
    private inscricaoService: InscricaoService,
    private eventoService: EventoService,
    private perguntaService: PerguntasService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.buscarEvento(params.id);
    });
  }

  buscarEvento(id: number) {
    this.perguntasEvento = [];

    this.eventoService.encontrarEventoPorId(id)
      .subscribe((evento: Evento) => {
        this.evento = evento;

        this.evento.perguntas.forEach(eventoPergunta => {
          this.buscarPerguntaDoEvento(eventoPergunta.idPergunta);
        });
      });
  }

  buscarPerguntaDoEvento(id: number) {
    this.perguntaService.buscarPergunta(id)
      .subscribe((pergunta: Pergunta) => {
        this.perguntasEvento.push(pergunta);
        console.log(this.perguntasEvento);
      });
  }

  salvarRespostasDoUsuario() {
    this.usuario = JSON.parse(window.localStorage.getItem("usuario"));
    let condicao = true;

    this.perguntasEvento.forEach(pergunta => {
      console.log(pergunta.resposta)
      if (!pergunta.resposta && pergunta.obrigatoriedade) {
        condicao = false;
        alert('Verifique se respondeu a todas as perguntas obrigatórias!')
        location.reload();
      }

      this.inscricaoResposta = new InscricaoResposta();
      this.inscricaoResposta.idEvento = this.evento.id
      this.inscricaoResposta.idPergunta = pergunta.id
      this.inscricaoResposta.idInscricao = null
      this.inscricaoResposta.resposta = pergunta.resposta

      this.inscricaoRespostas.push(this.inscricaoResposta)

    });

    if (condicao) {
      this.inscricao.idEvento = this.evento.id
      this.inscricao.idUsuario = this.usuario.id
      this.inscricao.idTipoSituacao = 1
      this.inscricaoRespostas.forEach(resposta => {
        this.inscricao.inscricaoRespostas.push(resposta)
      });
    }

    this.inscricaoService.salvarInscricao(this.inscricao)
      .subscribe(inscricao => {
        alert('Inscrição salva com sucesso')
      },
        (err: HttpErrorResponse) => {
          alert(err.error.message)
        })
    setTimeout(() => {
      this.router.navigate(['eventos/listagem'])
    }, 1500);

  }

}