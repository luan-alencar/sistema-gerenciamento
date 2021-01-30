import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Evento } from 'src/app/dominios/evento';
import { EventoService } from '../services/evento.service';

@Component({
  selector: 'app-evento-listagem',
  templateUrl: './evento-listagem.component.html',
  styleUrls: ['./evento-listagem.component.css']
})
export class EventoListagemComponent implements OnInit {

  // declaracoes
  eventos: Evento[] = [];
  formEvento: FormBuilder;
  edicao = false;

  // construtor
  constructor(
    // EventoService
    private eventoService: EventoService,
    // FormBuilder
    private fb: FormBuilder,
    // ActivatedRoute
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    // this.buscarEventos();
    this.route.
  }

  // Metodo para buscar os Eventos 
  private searchEventos() {
    this.eventoService.getAllEventos()
      .subscribe((eventos: Evento[]) => {
        this.eventos = eventos;
      });
  }

  // Metodo para deletar um Evento
  deletarEvento(id: number) {
    this.eventoService.deletarEvento(id)
      .subscribe(() => {
        alert('Evento deletado');
        this.buscarEventos();
      },
        err => alert(err));
  }

  // 
}