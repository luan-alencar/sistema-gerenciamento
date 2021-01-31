import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Evento } from 'src/app/dominios/evento';
import { TipoEvento } from 'src/app/dominios/tipo-evento';
import { EventoService } from '../services/evento.service';


@Component({
  selector: 'app-evento-formulario',
  templateUrl: './evento-formulario.component.html',
  styleUrls: ['./evento-formulario.component.css']
})
export class EventoFormularioComponent implements OnInit {

  // Formulario Reativo: encurta a cricao de instancias de um FormControl
  formEvento: FormGroup;

  @Input() evento = new Evento();

  @Input() edicao = false;
  @Input() valueCheck: boolean;
  @Input() tipoEventos: TipoEvento[] = [];
  @Output() selectTipoEvento: TipoEvento;

  @Output() eventoSalvo = new EventEmitter<Evento>();

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private eventoService: EventoService
  ) {
    this.tipoEventos = [
      { id: 1, descricao: 'Workshop' },
      { id: 2, descricao: 'Reunião' },
      { id: 3, descricao: 'Mini Curso' },
      { id: 4, descricao: 'Palestra' },
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
      titulo: '',
      local: '',
      descricao: '',
      qtdVagas: '',
      tipoInscricao: this.valueCheck,
      idTipoEvento: this.tipoEventos,
      valor: '',
      dataInicio: '',
      dataFim: '',
    });
  } // Fim ngOnInit

  searchEvento(id: number) {
    this.eventoService.findEventoById(id)
      .subscribe(evento => this.evento = evento);
  }


  postEvento() {
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

  closeDialog(eventoSalvo: Evento) {
    this.eventoSalvo.emit(eventoSalvo);
  }

}