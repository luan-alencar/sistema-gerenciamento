import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/modulos/usuario/services/usuario.service';
import { Usuario } from '../../../dominios/usuario';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  //styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  @Input() usuario = new Usuario();
  edicao = false;
  usuarioLogado = JSON.parse(localStorage.getItem('usuario'));
  @Output() usuarioSalvo = new EventEmitter<Usuario>();

  cadastroUsuario: FormGroup;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    if (this.usuarioLogado) {
      this.usuario = this.usuarioLogado;
      this.edicao = true;
    }

    this.route.params.subscribe(params => {
      if (params.id) {
        this.edicao = true;
        this.buscarUsuarioPorId(params.id);
      }
    });

    this.cadastroUsuario = this.fb.group({
      nome: ['', Validators.minLength(3)],
      cpf: ['', [Validators.maxLength(11), Validators.minLength(11)]],
      email: ['', Validators.email],
      telefone: '',
      dataNascimento: '',
    });
  }

  buscarUsuarioPorId(id: number) {
    this.usuarioService.buscarUsuarioPorId(id)
      .subscribe(usuario => {
        this.usuario = usuario;
        console.log(this.usuario);
      });
  }

  salvar() {
    if (this.cadastroUsuario.invalid) {
      alert('formulario invalido')
      return;
    }

    if (this.edicao) {
      this.usuarioService.editarUsuario(this.usuario)
        .subscribe(usuario => {
          console.log("usuario salvo", usuario);
          alert('Usuario salvo')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
    } else {
      this.usuarioService.salvarUsuario(this.usuario)
        .subscribe(usuario => {
          console.log("usuario salvo", usuario);
          alert('Usuario salvo')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
    }

  }

  fecharDialog(usuarioSalvo: Usuario) {
    this.usuarioSalvo.emit(usuarioSalvo);
  }

}
