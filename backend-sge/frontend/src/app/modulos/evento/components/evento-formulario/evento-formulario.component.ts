import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ConfirmationService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
import { EventoPergunta } from 'src/app/dominios/evento-pergunta';
import { Pergunta } from 'src/app/dominios/pergunta';
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
  pergunta = new Pergunta();
  edicao = false;
  tipoInscricao = false;
  valueCheck: boolean;
  display: boolean;
  perguntaAdd: boolean;
  checagemDeObrigatoriedadePergunta: boolean;
  tipoEventos: TipoEvento[] = [];
  perguntasEvento: Pergunta[] = [];
  perguntaEventoPergunta: EventoPergunta;

  selectTipoEvento: TipoEvento;


  @Output() eventoSalvo = new EventEmitter<Evento>();
  @Output() perguntaSalva = new EventEmitter<Pergunta>();

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private eventoService: EventoService,
    private confirmationService: ConfirmationService
  ) {
    this.tipoEventos = [
      { id: 1, descricao: 'Workshop' },
      { id: 2, descricao: 'Minicurso' },
      { id: 3, descricao: 'Treinamento' },
      { id: 4, descricao: 'Palestra' }
    ],
      this.perguntasEvento = [
        { id: null, titulo: null, obrigatoriedade: null }
      ]
  }

  // Inicio ngOnInit
  ngOnInit(): void {

    this.route.params.subscribe(params => {
      if (params.id) {
        this.edicao = true;
        this.searchEvento(params.id);
      }
    });

    // Criando o Formulario Reativo(FormBuilder) que recebe uma rota associada
    // a um componente carregado. No caso, EventoFormularioComponent.
    this.formEvento = this.fb.group({
      titulo : '',
      periodoInicio: '',
      periodoFim: '',
      tipoInsc: '',
      descricao: '',
      qtdVagas: '',
      idTipoEvento: '',
      valor: '',
      local: '',
      eventoPerguntas: '',
      pergunta: ''
    });

  } // Fim ngOnInit

  showDialog() {
    this.display = true;
  }

  closeDialog(perguntaSalva: Pergunta) {
    this.perguntaSalva.emit(perguntaSalva);
  }

  salvarPergunta() {
    this.perguntaAdd = true;
  }

  searchEvento(id: number) {
    this.eventoService.findEventoById(id)
      .subscribe(evento => this.evento = evento);
  }

  criarDropDown() {
    this.tipoEventos.forEach(params => {
      return {
        label: params.descricao,
        value: params.id
      }
    });
  }

  salvar() {
    this.confirmationService.confirm({
      message: 'Voce deseja confirmar o cadastro?',
      accept: () => {

        this.evento.tipoEvento = this.selectTipoEvento.id;
        this.evento.tipoInscricao = this.tipoInscricao;
        console.log(this.evento);
        if (this.formEvento.invalid) {
          alert('Formulário Inválido');
          return;
        }
        if (this.edicao) {
          this.eventoService.putEvento(this.evento)
            .subscribe(evento => {
              console.log('Evento cadastrado!', evento);
              alert('Evento cadastrado')
            }, (erro: HttpErrorResponse) => {
              alert(erro.error.message);
            });
        } else {
          this.eventoService.postEvento(this.evento)
            .subscribe(evento => {
              console.log('Evento cadastrado!', evento);
              alert('Evento cadastrado')
            }, (erro: HttpErrorResponse) => {
              alert(erro.error.message);
            });
        }
      }
    });
  }
}