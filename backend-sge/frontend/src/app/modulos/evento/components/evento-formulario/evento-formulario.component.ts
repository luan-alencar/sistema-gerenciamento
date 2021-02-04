import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ConfirmationService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
import { EventoPergunta } from 'src/app/dominios/evento-pergunta';
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


  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private eventoService: EventoService,
    private confirmationService: ConfirmationService,
    private perguntaService: PerguntasService
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

    this.buscarTipoEvento();
    this.buscarPerguntas();

    this.route.params.subscribe(params => {
      if (params.id) {
        this.edicao = true;
        this.buscarEvento(params.id);
      }
    });

    // Criando o Formulario Reativo(FormBuilder) que recebe uma rota associada
    // a um componente carregado. No caso, EventoFormularioComponent.
    this.formEvento = this.fb.group({
      titulo: '',
      local: '',
      descricao: '',
      qtdVagas: '',
      tipoEvento: '',
      tipoInscricao: '',
      valor: '',
      dataInicio: '',
      dataFim: '',
      eventoPerguntas: '',
      pergunta: ''
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
    this.eventoService.findEventoById(id)
      .subscribe(evento => this.evento = evento);
  }

  salvarEvento() {
    if (this.formEvento.invalid) {
      alert('Formulário Inválido!');
      return;
    }

    if (this.edicao) {
      this.eventoService.putEvento(this.evento)
        .subscribe(evento => {
          alert('Evento Editado!');
          this.fecharDialog(evento);
        }, (erro: HttpErrorResponse) => {
          (erro.error.message);
        });
    }

    this.confirmationService.confirm({
      message: 'Voce deseja confirmar o cadastro?',
      accept: () => {

        this.evento.tipoEvento = this.eventoTipo.id
        this.evento.tipoInscricao = this.inscricaoTipo
        console.log(this.evento);
        this.perguntasEvento.forEach(perg => {
          this.perguntaEventoPergunta = new EventoPergunta()
          this.perguntaEventoPergunta.idEvento = null
          this.perguntaEventoPergunta.idPergunta = perg.id
          this.evento.perguntas.push(this.perguntaEventoPergunta);
        });
        this.eventoService.postEvento(this.evento)
          .subscribe(evento => {
            alert('Evento Salvo!');
            console.log(evento)
          }, (erro: HttpErrorResponse) => {
            alert(erro.error.message)
          });
      }
    });

  }

  salvarPergunta(pergunta: Pergunta) {
    this.perguntaService.postPergunta(pergunta)
      .subscribe(() => {
        alert('Pergunta salva!');
        console.log(pergunta);
        this.perguntaAdd = false;
      }, (erro: HttpErrorResponse) => {
        alert(erro.error.message);
      });
  }

  buscarTipoEvento() {
    this.eventoService.getAllTipoEvento()
      .subscribe((tipoEventos: TipoEvento[]) => {
        this.tipoEventos = tipoEventos;
      });
  }

  buscarPerguntas() {
    this.perguntaService.getAllPerguntas()
      .subscribe((perguntas: Pergunta[]) => {
        this.perguntasEvento = perguntas;
      })
  }
}