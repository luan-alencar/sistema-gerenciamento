import { EventoService } from './service/evento.service';
import { Component, OnInit } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  eventos: Evento[] = [];

  constructor(private servico: HttpClient) { }

  ngOnInit(): void {
    this.buscarEventos();
  }

  private buscarEventos() {
    return this.servico.getEventos()
      .subscribe((eventos: Evento[]) => {
        console.log('subscribe-eventos');
        this.eventos = eventos;
      });
  }
}
