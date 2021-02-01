import { EventoFormularioComponent } from './../evento-formulario/evento-formulario.component';
import { Component, OnInit } from '@angular/core';
import { TipoEvento } from 'src/app/dominios/tipo-evento';
import { EventoService } from '../services/evento.service';

@Component({
  selector: 'app-perguntas-cadastro',
  templateUrl: './perguntas-cadastro.component.html',
  styleUrls: ['./perguntas-cadastro.component.css']
})
export class PerguntasCadastroComponent implements OnInit {

  tipoEventos: TipoEvento[] = [];

  constructor(
    private eventoService: EventoService
  ) {
    this.tipoEventos = [
      { id: 1, descricao: 'Workshop' },
      { id: 2, descricao: 'Reunião' },
      { id: 3, descricao: 'Mini Curso' },
      { id: 4, descricao: 'Palestra' }
    ]
  }

  ngOnInit(): void {

  }

  // cria um dropdown para selecionar o Evento
  createDropDown() {
    this.tipoEventos.forEach(params => {
      return {
        label: params.descricao,
        value: params.id
      }
    });
  }

  salvarPerguntaEvento(){
    this.eventoService.postEvento
  }

}
