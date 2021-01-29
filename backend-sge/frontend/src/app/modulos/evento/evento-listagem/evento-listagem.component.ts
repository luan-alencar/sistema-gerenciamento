import { Component, OnInit } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { EventoService } from '../services/evento.service';

@Component({
  selector: 'app-evento-listagem',
  templateUrl: './evento-listagem.component.html',
  styleUrls: ['./evento-listagem.component.css']
})
export class EventoListagemComponent implements OnInit {

  eventos: Evento[] = [];

  constructor(private eventoService: EventoService) { }

  ngOnInit() {
    this.buscarEventos();
  }

  private buscarEventos() {
    this.eventoService.getEventos()
      .subscribe((eventos: Evento[]) => {
        this.eventos = eventos;
      });
  }

  deletarEvento(id: number) {
    this.eventoService.deletarEvento(id)
      .subscribe(() => {
        alert('Evento deletado');
        this.buscarEventos();
      },
        err => alert(err));
  }
}