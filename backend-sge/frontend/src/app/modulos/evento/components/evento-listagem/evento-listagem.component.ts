import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
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

  ngOnInit(): void {
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
        this.mostrarDialog(true);
      });
  }

  mostrarDialogCadastrar() {
    this.evento = new Evento();
    this.mostrarDialog();
  }

  mostrarDialog(edicao = false) {
    this.exibirDialog = true;
    this.formularioEdicao = edicao;
  }

  confirmarDeletarEvento(id: number) {
    this.confirmationService.confirm({
      message: 'Deseja realmente excluir este Evento?',
      accept: () => {
        this.deleteEvento(id);
      }
    });
  }

  fecharDialog() {
    this.exibirDialog = false;
    this.buscarEventos();
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