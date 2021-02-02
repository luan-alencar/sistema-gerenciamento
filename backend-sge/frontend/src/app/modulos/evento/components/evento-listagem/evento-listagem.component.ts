import { Component, OnInit } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { EventoService } from '../../services/evento.service';

@Component({
  selector: 'app-evento-listagem',
  templateUrl: './evento-listagem.component.html',
  styleUrls: ['./evento-listagem.component.css']
})
export class EventoListagemComponent implements OnInit {

  // declaracoes
  eventos: Evento[] = [];

  // construtor
  constructor(
    private eventoService: EventoService,
  ) { }

  ngOnInit() {
    this.buscarEventos();
  }

  private buscarEventos() {
    this.eventoService.getAllEventos()
      .subscribe((eventos: Evento[]) => {
        this.eventos = eventos;
      });
  }

  deleteEvento(id: number) {
    this.eventoService.deleteEvento(id)
      .subscribe(() => {
        alert('Evento deletado');
        this.buscarEventos();
      },
        err => alert(err));
  }
}