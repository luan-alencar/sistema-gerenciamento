import { UsuarioService } from '../../service/usuario.service';
import { Usuario } from '../../../../dominios/usuario';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  formUsuario: FormGroup;
  usuario = new Usuario();
  constructor(
    private fb: FormBuilder,
    private usuarioServico: UsuarioService
  ) { }

  ngOnInit(): void {
    this.formUsuario = this.fb.group({
      nome: ['', Validators.minLength(3)],
      cpf: '',
      email: '',
      telefone: '',
      dtNasc: '',
    },)
  }

  salvar() {
    if (this.formUsuario.invalid) {
      alert('Formulário inválido!');
      return;
    }
    this.usuarioServico.salvarUsuario(this.usuario)
      .subscribe(usuario => {
        console.log('usuario salvo', usuario);
        alert('Usuário salvo')
      }, (erro: HttpErrorResponse) => {
        alert(erro.error.message);
      });
  }
}
