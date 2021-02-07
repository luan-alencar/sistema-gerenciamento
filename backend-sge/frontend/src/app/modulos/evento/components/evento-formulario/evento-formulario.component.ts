import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
import { EventoPergunta } from 'src/app/dominios/evento-pergunta';
import { Inscricao } from 'src/app/dominios/inscricao';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntasService } from 'src/app/modulos/perguntas/services/perguntas.service';
import { EventoService } from '../../services/evento.service';
import { TipoEvento } from './../../../../dominios/tipo-evento';


@Component({
  selector: 'app-evento-formulario',
  templateUrl: './evento-formulario.component.html',
  styleUrls: ['./evento-formulario.component.css']
})
export class EventoFormularioComponent implements OnInit {

  // Formulario Reativo: encurta a cricao de instancias de um FormControl
  formEvento: FormGroup;

  // instancias
  @Input() evento = new Evento();
  @Input() edicao = false;
  @Output() eventoSalvo = new EventEmitter<Evento>();
  @Output() inscricaoSalva = new EventEmitter<Inscricao>();

  pergunta = new Pergunta();

  inscricaoTipo = false;

  valueCheck: boolean;
  display: boolean;
  perguntaAdd: boolean;
  checagemDeObrigatoriedadePergunta: boolean;


  eventoTipo: TipoEvento;
  tipoEventos: TipoEvento[] = [];
  perguntasEvento: Pergunta[] = [];
  perguntaEventoPergunta: EventoPergunta;

  perguntasEventoSelecionadas: Pergunta[] = [];

  perguntas: Pergunta[] = []
  perguntaEscolhidas: Pergunta[] = []
  eventoPergunta: EventoPergunta
  adicionarPergunta: boolean = false

  tipoEvento: TipoEvento

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private eventoService: EventoService,
    private confirmationService: ConfirmationService,
    private perguntaService: PerguntasService,
    private router: Router,
    private messageService: MessageService

  ) {
    // this.tipoEventos = [
    //   { id: 1, descricao: 'Workshop' },
    //   { id: 2, descricao: 'Minicurso' },
    //   { id: 3, descricao: 'Treinamento' },
    //   { id: 4, descricao: 'Palestra' }
    // ]
  }

  // Inicio ngOnInit
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params.id) {
        this.edicao = true;
        this.buscarEvento(params.id);
      }
    });

    this.buscarTipoEvento();
    this.buscarPerguntas();

    // Criando o Formulario Reativo(FormBuilder) que recebe uma rota associada
    // a um componente carregado. No caso, EventoFormularioComponent.
    this.formEvento = this.fb.group({
      titulo: ['', Validators.required],
      dataInicio: ['', Validators.required],
      dataFim: ['', Validators.required],
      tipoInscricao: ['', Validators.required],
      descricao: ['', Validators.required],
      qtdVagas: '',
      tipoEvento: ['', Validators.required],
      valor: '',
      local: ['', Validators.required],
      eventoPerguntas: '',
      pergunta: '',
      obrigatoriedade: ''
    });

  } // Fim ngOnInit

  showDialog() {
    this.display = true;
  }

  fecharDialog(eventoSalvo: Evento) {
    this.eventoSalvo.emit(eventoSalvo);
  }

  addPergunta() {
    this.perguntaAdd = true;
  }

  buscarEvento(id: number) {
    this.eventoService.encontrarEventoPorId(id)
      .subscribe(evento => this.evento = evento);
  }

  confirm() {
    this.confirmationService.confirm({
      message: 'Deseja salvar mesmo esse evento?',
      accept: () => {
        this.salvar()
      }
    });
  }

  salvar() {
    this.evento.tipoEvento = this.eventoTipo.id
    this.evento.tipoInscricao = this.inscricaoTipo

    if (this.evento.valor == null) {
      this.evento.valor = 0
    }

    this.perguntaEscolhidas.forEach(pergunta => {
      this.eventoPergunta = new EventoPergunta
      this.eventoPergunta.idEvento = null
      this.eventoPergunta.idPergunta = pergunta.id

      this.evento.perguntas.push(this.eventoPergunta)
    });

    if (this.edicao) {
      this.eventoService.editarEvento(this.evento).subscribe(
        evento => {
          alert("evento salvo com sucesso")
          setTimeout(() => {
            this.router.navigate(['/eventos/listagem'])
          }, 1500)
        }, erro => {
          alert("dados inválidos")

        });
    } else {
      this.eventoService.salvarEvento(this.evento).subscribe(
        evento => {
          alert("evento salvo com sucesso")
          setTimeout(() => {
            this.router.navigate(['/eventos/listagem'])
          }, 1500)
        }, erro => {
          alert("dados inválidos")

        }
      )
    }
  }

  salvarPergunta(pergunta: Pergunta) {
    console.log(pergunta)
    if (pergunta.obrigatoriedade == null) {
      pergunta.obrigatoriedade = false;
    }
    this.perguntaService.salvarPergunta(pergunta)
      .subscribe(() => {
        alert('Pergunta salva!');
        this.perguntaAdd = false;
        console.log(pergunta)
      }, (erro: HttpErrorResponse) => {
        alert(erro.error.message);
      });
  }

  buscarTipoEvento() {
    this.eventoService.buscarTodosTipoEvento()
      .subscribe((tipoEventos: TipoEvento[]) => {
        this.tipoEventos = tipoEventos;
      });
  }

  buscarPerguntas() {
    this.perguntaService.buscarTodasPerguntas()
      .subscribe((perguntas: Pergunta[]) => {
        this.perguntasEvento = perguntas;
      })
  }
}