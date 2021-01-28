import { EventoService } from './service/evento.service';
import { Component, OnInit } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { ConfirmationService, MessageService } from 'primeng';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css'],
  styles: [`
        :host ::ng-deep .p-dialog .product-image {
            width: 150px;
            margin: 0 auto 2rem auto;
            display: block;
        }
    `],
  providers: [MessageService, ConfirmationService]
})

export class FormularioComponent implements OnInit {

  eventoDialog: boolean;

  eventos: Evento[] = [];

  evento: Evento;

  selectedEventos: Evento[] = [];

  submitted: boolean;

  statuses: any[];

  constructor(private eventoService: EventoService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    // Pq este metodo then() esta com o erro?
    this.eventoService.getEventos().then(data => this.eventos = data);

    this.statuses = [
      { label: 'INSTOCK', value: 'instock' },
      { label: 'LOWSTOCK', value: 'lowstock' },
      { label: 'OUTOFSTOCK', value: 'outofstock' }
    ];
  }

  openNew() {
    this.evento = {};
    this.submitted = false;
    this.eventoDialog = true;
  }

  deleteSelectedEventos() {
    this.confirmationService.confirm({
      message: 'Você tem certeza que deseja excluir estes eventos?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.eventos = this.eventos.filter(val => !this.selectedEventos.includes(val));
        this.selectedEventos = null;
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
      }
    });
  }

  editEvento(evento: Evento) {
    this.evento = { ...evento };
    this.eventoDialog = true;
  }

  deleteEvento(evento: Evento) {
    this.confirmationService.confirm({
      message: 'Você tem certeza que deseja deletar este Evento?' + evento.id + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        // this.products = this.products.filter(val => val.id !== product.id);
        this.eventos = this.eventos.filter(val => val.id !== evento.id);
        this.evento = {};
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Evento Deletado', life: 3000 });
      }
    });
  }

  hideDialog() {
    this.eventoDialog = false;
    this.submitted = false;
  }

  saveEvento() {
    this.submitted = true;

    // como utilizar nete if um id number ?
    if (this.evento.titulo.trim()) {
      if (this.evento.id) {
        this.eventos[this.findIndexById(this.evento.id)] = this.evento;
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Evento Atualizado!', life: 3000 });
      }
      else {
        this.evento.titulo = this.createId();
        this.eventos.push(this.evento);
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Evento Criado!', life: 3000 });
      }

      this.eventos = [...this.eventos];
      this.eventoDialog = false;
      this.evento = {};
    }
  }

  findIndexById(id: number): number {
    let index = -1;
    for (let i = 0; i < this.eventos.length; i++) {
      if (this.eventos[i].id === id) {
        index = i;
        break;
      }
    }
    return index;
  }

  createId(): string {
    let id = '';
    var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    for (var i = 0; i < 5; i++) {
      id += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return id;
  }
}



