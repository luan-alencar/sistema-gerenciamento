import { Usuario } from './../../../../dominios/usuario';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
import { Inscricao } from 'src/app/dominios/inscricao';
import { PerguntasService } from 'src/app/modulos/perguntas/services/perguntas.service';
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
  inscricao = new Inscricao();

  idEventoSelecionado: number;

  exibirDialog = false;
  exibirDialogInscricao = false;

  formularioInscricao: boolean;
  formularioEdicao: boolean;
  usuario: Usuario;

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


  pegarUsuarioLocalStorage() {
    const usuario = JSON.parse(window.localStorage.getItem("usuario")); 
    this.usuario = usuario;

  private buscarPerguntas() {
    this.perguntasService.buscarTodasPerguntas();
  }

  fecharDialog() {
    this.exibirDialog = false;
    this.buscarEventos();
  }

  mostrarDialogInscricao(evento: Evento) {
    this.idEventoSelecionado = evento.id;
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

  fecharDialogInscricao() {
    this.exibirDialog = false;
    this.buscarPerguntas();
  }

  confirmarDeletarEvento(id: number) {
    this.confirmationService.confirm({
      message: 'Deseja realmente excluir este Evento?',
      accept: () => {
        this.deleteEvento(id);
      }
    });
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