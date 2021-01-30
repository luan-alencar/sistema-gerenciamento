import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuario } from './../../../dominios/usuario';
import { Component, Input, OnInit } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() usuario = new Usuario();

  loginUsuario: FormGroup

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      if(params.email){
        this.buscarUsuarioPorEmail(params.email);
      }
    });

    this.loginUsuario = this.fb.group({
      email: ['', Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')],
      chave: ''
    })

  }

  buscarUsuarioPorEmail(email: string){
    this.usuarioService.buscarUsuarioPorEmail(email)
      .subscribe(usuario => {
        this.usuario = usuario;
      });
  }

  entrar(email: string, chave: string){
    if(this.loginUsuario.invalid){
      alert('email inválido');
      return;
    }else{
      if(chave != this.usuario.chave){
        alert('senha ou chave de acesso incorretos');
        return;
      }
    }
  }

}
