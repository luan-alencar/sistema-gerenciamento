import { EventoService } from './../../../evento/services/evento.service';
import { InscricaoService } from './../../services/inscricao.service';
import { ActivatedRoute } from '@angular/router';
import { Evento } from './../../../../dominios/evento';
import { Inscricao } from 'src/app/dominios/inscricao';
import { Usuario } from './../../../../dominios/usuario';
import { InscricaoUsuario } from './../../../../dominios/inscricao-usuario';
import { InscricoesLista } from './../../../../dominios/inscricoes-lista';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-inscricao-listagem',
  templateUrl: './inscricao-listagem.component.html',
  styleUrls: ['./inscricao-listagem.component.css']
})
export class InscricaoListagemComponent implements OnInit {

  inscricoes: InscricoesLista[] = [];
  inscricaoUsuario: InscricaoUsuario[] = [];
  usuario = new Usuario();
  inscricao = new Inscricao();
  @Input() evento = new Evento();

  constructor(
    private route: ActivatedRoute,
    private inscricaoService: InscricaoService,
    private eventoService: EventoService
  ) { }

  ngOnInit(): void {
    this.pegarUsuarioLocalStorage();

    if (this.usuario.admin) {
      this.route.params.subscribe(params => {
        this.buscarInscricaoPeloEventoId(params.id);
        this.buscarEvento(params.id);
      })
    } else {
      this.buscarInscricaoPeloUsuario();
    }
  }

  pegarUsuarioLocalStorage() {
    this.usuario = JSON.parse(window.localStorage.getItem("usuario"));
  }

  buscarEvento(id: number) {
    this.eventoService.encontrarEventoPorId(id)
      .subscribe((evento: Evento) => {
        this.evento = evento;
      });
  }

  buscarInscricaoPeloId(id: number){
    this.inscricaoService.encontrarInscricaoPorId(id)
      .subscribe((inscricao: Inscricao) => {
        this.inscricao = inscricao
      });
  }

  buscarInscricaoPeloEventoId(id: number) {
    this.inscricaoService.getInscricaoPeloEventoId(id)
      .subscribe((inscricoes: InscricoesLista[]) => {
        this.inscricoes = inscricoes;
      })
  }

  buscarInscricaoPeloUsuario() {
    this.inscricaoService.getInscricaoPeloUsuarioId(this.usuario.id)
      .subscribe((inscricoes: InscricaoUsuario[]) => {
        this.inscricaoUsuario = inscricoes;
      })
  }

  editarInscricaoCancelada(id: number) {
    this.inscricaoService.encontrarInscricaoPorId(id)
      .subscribe((inscricao: Inscricao) => {
        inscricao.idTipoSituacao = 4;
        this.inscricaoService.editarInscricao(inscricao)
          .subscribe(() => {
            alert("Inscrição cancelada")
          });
        location.reload();
      });
  }

  editarInscricaoAprovada(id: number){
    this.inscricaoService.encontrarInscricaoPorId(id)
      .subscribe((inscricao: Inscricao) => {
        inscricao.idTipoSituacao = 2;
        this.inscricaoService.editarInscricao(inscricao)
          .subscribe(() => {
            alert("Inscrição Aprovada")
          });
        location.reload();
      });
  }

  editarInscricaoReprovada(id: number){
    this.inscricaoService.encontrarInscricaoPorId(id)
      .subscribe((inscricao: Inscricao) => {
        inscricao.idTipoSituacao = 3;
        this.inscricaoService.editarInscricao(inscricao)
          .subscribe(() => {
            alert("Inscrição Reprovada")
          });
        location.reload();
      });
  }

}
