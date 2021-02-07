import { EventoPergunta } from 'src/app/dominios/evento-pergunta';
import { Pergunta } from 'src/app/dominios/pergunta';
import { TipoEvento } from 'src/app/dominios/tipo-evento';
import { FormGroup } from '@angular/forms';
import { Usuario } from './../../../../dominios/usuario';
import { Component, EventEmitter, OnInit, Output, Input } from '@angular/core';
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
  //evento = new Evento();
  inscricao = new Inscricao();

  idEventoSelecionado: number;

  exibirDialogInscricao = false;

  formularioInscricao: boolean;
  formularioEdicao: boolean;
  usuario: Usuario;

  edicao = false
  formEvento: FormGroup
  @Input() evento = new Evento
  tipoEventos: TipoEvento[] = []
  tipoInsc: boolean = false
  perguntas: Pergunta[] = []
  perguntaEscolhidas: Pergunta[] = []
  eventoPergunta: EventoPergunta
  adicionarPergunta: boolean = false
  pergunta = new Pergunta
  display: boolean = false;

  tipoEvento: String

  // construtor
  constructor(
    private eventoService: EventoService,
    private confirmationService: ConfirmationService,
    private perguntasService: PerguntasService
  ) { }

  ngOnInit(): void {
    this.buscarEventos();
    this.buscarPerguntas();
    this.pegarUsuarioLocalStorage();
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
  }

  buscarPerguntas() {
    this.perguntasService.buscarTodasPerguntas();
  }

  buscarPerguntasDoEvento(eventoPerguntas: EventoPergunta[]){
    this.perguntas = []
    eventoPerguntas.forEach(pergunta => {
      this.perguntasService.buscarPergunta(pergunta.idPergunta).subscribe((pergunta2 => {
        this.perguntas.push(pergunta2)
      }))
    });
  }

  fecharDialog() {
    this.display = false;
    this.buscarEventos();
  }

  adicionarPerguntas(){
    this.adicionarPergunta = true
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
    this.display = true;
    this.formularioEdicao = edicao;
  }

  fecharDialogInscricao() {
    this.display = false;
    this.buscarPerguntas();
  }

  showDialog(id: number) {
    this.eventoService.encontrarEventoPorId(id)
      .subscribe(evento => {
        this.evento = evento
        this.buscarTipoEvento(evento.tipoEvento)
        this.buscarPerguntasDoEvento(evento.perguntas)
        this.display = true;
      }); 
  }

  buscarTipoEvento(id: number){
    this.eventoService.encontrarEventoPorId(id).subscribe((tipoEvento: TipoEvento ) => { 
      this.tipoEvento = tipoEvento.descricao;
    }) 
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