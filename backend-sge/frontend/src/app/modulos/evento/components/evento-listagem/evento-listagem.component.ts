import { ConfirmationService } from 'primeng';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { Usuario } from 'src/app/dominios/usuario';
import { EventoService } from '../../services/evento.service';

@Component({
  selector: 'app-evento-listagem',
  templateUrl: './evento-listagem.component.html',
  styleUrls: ['./evento-listagem.component.css']
})
export class EventoListagemComponent implements OnInit {

  // declaracoes
  @Output() eventoSalvo = new EventEmitter<Evento>();
  eventos: Evento[] = [];
  evento = new Evento();
  exibirDialog = false;
  formularioEdicao: boolean;

  // construtor
  constructor(
    private eventoService: EventoService,
    private confirmationService: ConfirmationService
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

  mostrarDialogEditar(id: number) {
    this.eventoService.findEventoById(id)
      .subscribe(evento => {
        this.evento = evento;
      });
  }

  mostrarDialog(edicao = false) {
    this.exibirDialog = false;
    this.buscarEventos();
  }

  confirmarDeletarEvento(id: number) {
    this.confirmationService.confirm({
      message: 'Deseja realmente excluir este Evento?',
      accept: () => {
        this.deleteEvento(id);
      }
    });
  }

  fecharDialog(eventoSalvo: Evento) {
    this.eventoSalvo.emit(eventoSalvo);
  }

  deleteEvento(id?: number) {
    this.eventoService.deleteEvento(id)
      .subscribe(() => {
        alert('Evento deletado');
        this.buscarEventos();
      },
        err => alert(err));
  }
}