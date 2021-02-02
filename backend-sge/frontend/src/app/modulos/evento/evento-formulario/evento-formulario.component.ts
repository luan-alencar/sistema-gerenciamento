import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Evento } from 'src/app/dominios/evento';
import { Pergunta } from 'src/app/dominios/pergunta';
import { EventoService } from '../services/evento.service';
import { TipoEvento } from './../../../dominios/tipo-evento';


@Component({
  selector: 'app-evento-formulario',
  templateUrl: './evento-formulario.component.html',
  styleUrls: ['./evento-formulario.component.css']
})
export class EventoFormularioComponent implements OnInit {

  // Formulario Reativo: encurta a cricao de instancias de um FormControl
  formEvento: FormGroup;

  evento = new Evento();
  pergunta = new Pergunta();
  edicao = false;
  valueCheck: boolean;
  display: boolean;
  checagemDeObrigatoriedadePergunta =  true;
  selectTipoEvento: TipoEvento;

  tipoEventos: TipoEvento[] = [];
  perguntasEvento: Pergunta[] = [];

  @Output() eventoSalvo = new EventEmitter<Evento>();

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private eventoService: EventoService,
    private router: Router
  ) {
    this.tipoEventos = [
      { id: 1, descricao: 'Workshop' },
      { id: 2, descricao: 'Reunião' },
      { id: 3, descricao: 'Mini Curso' },
      { id: 4, descricao: 'Palestra' }
    ]

  }

  // Inicio ngOnInit
  ngOnInit(): void {
    // this.createDropDown();

    this.route.params.subscribe(params => {
      if (params.id) {
        this.edicao = true;
        this.searchEvento(params.id);
      }
    });

    // Criando o Formulario Reativo(FormBuilder) que recebe uma rota associada
    // a um componente carregado. No caso, EventoFormularioComponent.
    this.formEvento = this.fb.group({
      titulo: '',
      local: '',
      descricao: '',
      qtdVagas: '',
      tipoInscricao: null,
      tipoEvento: null,
      valor: '',
      dataInicio: '',
      dataFim: '',
    });


  } // Fim ngOnInit

  showDialog() {
    this.display = true;
  }

  closeDialog(eventoSalvo: Evento) {
    this.eventoSalvo.emit(eventoSalvo);
  }

  searchEvento(id: number) {
    this.eventoService.findEventoById(id)
      .subscribe(evento => this.evento = evento);
  }

  postEvento() {
    this.evento.tipoEvento = this.selectTipoEvento.id;
    // this.evento.perguntas = this.perguntasEvento;
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

  criarDropDown() {
    this.tipoEventos.forEach(params => {
      return {
        label: params.descricao,
        value: params.id
      }
    });
  }

  criarPerguntas() {
    this.router.navigate([`eventos/evento-formularios/cadastro-perguntas`])
  }
}