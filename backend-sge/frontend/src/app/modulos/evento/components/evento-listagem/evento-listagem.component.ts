import { PerguntasService } from 'src/app/modulos/perguntas/services/perguntas.service';
import { InscricaoService } from './../../../inscricao/services/inscricao.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
import { EventoService } from '../../services/evento.service';
import { Inscricao } from 'src/app/dominios/inscricao';

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
  inscricao = new Inscricao();

  idEventoSelecionado: number;

  exibirDialog = false;
  exibirDialogInscricao = false;
  
  formularioInscricao: boolean;
  formularioEdicao: boolean;

  // construtor
  constructor(
    private eventoService: EventoService,
    private confirmationService: ConfirmationService,
    private perguntasService: PerguntasService
  ) { }

  ngOnInit(): void {
    this.buscarEventos();
    this.buscarPerguntas();
  }

  private buscarEventos() {
    this.eventoService.buscarTodosEventos()
      .subscribe((eventos: Evento[]) => {
        this.eventos = eventos;
      });
  }

  private buscarPerguntas() {
    this.perguntasService.buscarTodasPerguntas();
  }

  mostrarDialogInscricao(evento: Evento) {
    this.idEventoSelecionado = evento.id;
    console.log(evento);
    this.exibirDialogInscricao = true;
    this.formularioInscricao = true;
  }

  mostrarDialogEditar(id: number) {
    this.eventoService.encontrarEventoPorId(id)
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

  fecharDialogInscricao() {
    this.exibirDialog = false;
    this.buscarPerguntas();
  }

  deleteEvento(id?: number) {
    this.eventoService.deletarEvento(id)
      .subscribe(() => {
        alert('Evento deletado');
        this.buscarEventos();
      },
        err => alert(err));
  }
}