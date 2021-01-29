import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../service/usuario.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
})
export class ListagemComponent implements OnInit {

  usuarios: Usuario[] = [];

  constructor(private usuarioServico: UsuarioService) { }

  ngOnInit(): void {
    this.buscarUsuarios();
  }

  private buscarUsuarios() {
    this.usuarioServico.getUsuarios()
      .subscribe((usuarios: Usuario[]) => {
        this.usuarios = usuarios;
      });
  }

  deletarUsuario(id: number) {
    this.usuarioServico.deletarUsuario(id)
      .subscribe(() => {
        alert('Usuário deletado!');
        this.buscarUsuarios();
      },
        err => alert(err));
  }
}