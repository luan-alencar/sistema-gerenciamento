import { UsuarioService } from '../../service/usuario.service';
import { Usuario } from '../../../../dominios/usuario';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  edicao = false;
  formUsuario: FormGroup;
  usuario = new Usuario();

  constructor(
    private fb: FormBuilder,
    private usuarioServico: UsuarioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      if (params.id) {
        this.edicao = true;
        this.buscarUsuario(params.id);
      }
    });

    this.formUsuario = this.fb.group({
      nome: ['', Validators.minLength(3)],
      cpf: '',
      email: '',
      telefone: '',
      dtNasc: '',
    })
  }

  buscarUsuario(id: number) {
    this.usuarioServico.buscarUsuarioPorId(id)
      .subscribe(usuario => this.usuario = usuario);
  }

  salvar() {
    if (this.formUsuario.invalid) {
      alert('Formulário inválido!');
      return;
    }
    if (this.edicao) {
      this.usuarioServico.editarUsuario(this.usuario)
        .subscribe(usuario => {
          alert('Usuário editado!');
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
    } else {
      this.usuarioServico.salvarUsuario(this.usuario)
        .subscribe(usuario => {
          console.log('usuario salvo', usuario);
          alert('Usuário salvo')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
    }
  }
}
