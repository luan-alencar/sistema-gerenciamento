import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
})
export class ListagemComponent implements OnInit {

  usuarios: Usuario[] = [];
  usuario = new Usuario();
  exibirDialog = false;
  formularioEdicao: boolean;

<<<<<<< HEAD
  constructor(
    private servico: UsuarioService,
    private confirmationService: ConfirmationService
  ) { }
=======

  constructor(public servico: UsuarioService) { }
>>>>>>> 089d6f124418304216683073ac78e3af0dc4b49c

  ngOnInit(): void {
    this.buscarUsuarios();
  }

  private buscarUsuarios(){
    this.servico.getUsuarios()
      .subscribe((usuarios: Usuario[]) => {
        this.usuarios = usuarios;
      });
  }

  mostrarDialogEditar(id: number) {
    this.servico.buscarUsuarioPorId(id)
      .subscribe(usuario => {
        this.usuario = usuario
        this.mostrarDialog(true);
      }); 
  }

  mostrarDialog(edicao = false) {
    this.exibirDialog = true;
    this.formularioEdicao = edicao;
  }

  fecharDialog(usuarioSalvo: Usuario) {
    console.log(usuarioSalvo);
    this.exibirDialog = false;
    this.buscarUsuarios();
  }

  confirmarDeletarUsuario(id: number) {
    this.confirmationService.confirm({
        message: 'Tem certeza que deseja remover usuário?',
        accept: () => {
            this.deletarUsuario(id);
        }
    });
  }

  private deletarUsuario(id: number){
    this.servico.deletarUsuario(id)
      .subscribe(() => {
        alert('Usuario removido');
        this.buscarUsuarios();
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