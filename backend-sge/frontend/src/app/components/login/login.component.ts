import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Usuario } from 'src/app/dominios/usuario';
import { Component, Input, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/modulos/usuario/services/usuario.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() usuario = new Usuario();

  loginUsuario: FormGroup;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      if(params.email){
        this.buscarUsuarioPorEmail(params.email);
        console.log(params.email);
      }
    });

    this.loginUsuario = this.fb.group({
      email: ['', Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')],
      chave: ''
    })

  }

  buscarUsuarioPorEmail(email: string){
    this.usuarioService.buscarUsuarioPorEmail(email)
      .subscribe(usuarios => {
        this.usuario = usuarios;
      });
  }

  entrar(email: string, chave: string){
    if(this.loginUsuario.invalid){
      alert('email inválido');
      return;
    }
      if(chave.toString() !== this.usuario.chave.toString()){
        alert('senha ou chave de acesso incorretos' + this.usuario.email + this.usuario.chave);
        return;
      }
    
    if(email.toString() === this.usuario.email.toString() && 
       chave.toString() === this.usuario.chave.toString()){
      alert('usuario válido');
      //this.usuarioService.loginSucesso();
    }
  }

}
