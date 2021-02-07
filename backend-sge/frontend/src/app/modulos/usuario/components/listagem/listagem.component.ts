import { InscricaoService } from './../../../inscricao/services/inscricao.service';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
})
export class ListagemComponent implements OnInit {

  @Output() emitUsuario: EventEmitter<Usuario> = new EventEmitter;
  @Input() usuario: Usuario;
  usuarios: Usuario[] = [];
  admin = false;
  exibirDialog: boolean = false;
  formularioEdicao: boolean;  //será que realmente precisa

  constructor(
    public servico: UsuarioService,
    public inscricaoService: InscricaoService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.buscarUsuarios();
    this.usuario = JSON.parse(localStorage.getItem('usuario'));
  }

  private buscarUsuarios() {
    this.servico.getUsuarios()
      .subscribe((usuarios: Usuario[]) => {
        this.usuarios = usuarios;
      },
        err => alert(err));
  }

  buscarUsuarioPorId(id: number) {
    this.servico.buscarUsuarioPorId(id)
      .subscribe((usuario: Usuario) => {
        this.usuario = usuario;
      },
        err => alert(err))
  }

  mostrarDialogEditar(id: number) {
    this.servico.buscarUsuarioPorId(id)
      .subscribe(usuario => {
        this.usuario = usuario
        this.mostrarDialog(true);
      });
  }

  edicao(usuarioEditado: Usuario) {
    this.exibirDialog = false
    localStorage.removeItem('usuario');
    localStorage.setItem("usuario", JSON.stringify(usuarioEditado));
    location.reload()
  }

  mostrarDialog(edicao = false) {
    this.exibirDialog = true;
    //this.formularioEdicao = edicao;
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
        this.deletarLoginUsuario(id);
        localStorage.removeItem("usuario")
      }
    });
  }

  public deletarUsuario(id: number) {
    if (this.usuario.admin) {
      alert('administrador não pode ser excluido');
    }
    if (!this.usuario.admin) {
      this.servico.deletarUsuario(id)
        .subscribe(() => {
          alert('Usuário deletado!');
          this.buscarUsuarios();
        },
          err => alert(err));
    }
  }

  deletarLoginUsuario(id: number) {
    this.servico.deletarUsuario(id)
      .subscribe(() => {
        alert('Usuário deletado');
        this.buscarUsuarios();
        localStorage.removeItem("usuario")
        location.reload()
      },
        err => alert(err))
  }
}
