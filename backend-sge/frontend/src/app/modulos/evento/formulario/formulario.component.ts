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
    providers: [MessageService,ConfirmationService]
})

export class FormularioComponent implements OnInit {

  eventos: Evento[] = [];

  eventoDialog: boolean;

    submitted: boolean;

    statuses: any[];
  selectedEventos: any;
  evento: { local: string; titulo: string; descricao: string; qtdVagas: number; valor: number; dataInicio: Date; dataFim: Date; tipoInscricao: boolean; idTipoEvento: number; perguntas: import("/home/luan/Documents/treinamento basis/sistema-gerenciamento/backend-sge/frontend/src/app/dominios/evento-pergunta").EventoPergunta[]; };

    constructor(public eventoService: EventoService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

    ngOnInit() {
        this.eventoService.getEventos().then(data => this.eventos = data);

        this.statuses = [
            {label: 'INSTOCK', value: 'instock'},
            {label: 'LOWSTOCK', value: 'lowstock'},
            {label: 'OUTOFSTOCK', value: 'outofstock'}
        ];
    }

    openNew() {
        this.eventos = [];
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
                this.messageService.add({severity:'success', summary: 'Successful', detail: 'Products Deleted', life: 3000});
            }
        });
    }

    editEvento(evento: Evento) {
        this.evento = {...this.evento};
        this.eventoDialog = true;
    }

    deleteEvento(evento: Evento) {
        this.confirmationService.confirm({
            message: 'Você tem certeza que deseja deletar este Evento?' + evento.titulo + '?',
            header: 'Confirm',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.eventos = this.eventos.filter(val => val.idTipoEvento !== evento.idTipoEvento);
                this.evento = {};
                this.messageService.add({severity:'success', summary: 'Successful', detail: 'Evento Deletado', life: 3000});
            }
        });
    }

    hideDialog() {
        this.eventoDialog = false;
        this.submitted = false;
    }
    
    saveEvento() {
        this.submitted = true;

        if (this.evento.local.trim()) {
            if (this.evento.perguntas) {
                this.eventos[this.findIndexById(this.evento.titulo)] = this.evento;                
                this.messageService.add({severity:'success', summary: 'Successful', detail: 'Evento Atualizado!', life: 3000});
            }
            else {
                this.evento.local = this.createId();
                this.eventos.push(this.evento);
                this.messageService.add({severity:'success', summary: 'Successful', detail: 'Evento Criado!', life: 3000});
            }

            this.eventos = [...this.eventos];
            this.eventoDialog = false;
            this.evento = {};
        }
    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.eventos.length; i++) {
            if (this.eventos[i].idTipoEvento === this.evento.idTipoEvento) {
                index = i;
                break;
            }
        }

        return index;
    }

    createId(): string {
        let id = '';
        var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        for ( var i = 0; i < 5; i++ ) {
            id += chars.charAt(Math.floor(Math.random() * chars.length));
        }
        return id;
    }
}

  

