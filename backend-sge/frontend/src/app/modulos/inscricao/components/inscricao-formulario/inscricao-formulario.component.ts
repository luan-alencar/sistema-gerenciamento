import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
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
  @Input('idEvento') set idEventofn(idEvento: number) {
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
  }

  buscarEvento(id: number) {

    this.eventoService.encontrarEventoPorId(id).subscribe((evento: Evento) => {
      this.evento = evento;

      this.evento.perguntas.forEach(eventoPergunta => {
        this.buscarTodasPerguntasDoEvento(eventoPergunta.idPergunta);
      });
    });
  }
  buscarTodasPerguntasDoEvento(idPergunta: number) {
    this.perguntaService.buscarPergunta(idPergunta).subscribe((pergunta: Pergunta) => {
      this.perguntasEvento.push(pergunta);
    });
  }
}