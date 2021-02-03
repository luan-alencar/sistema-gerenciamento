import { UsuarioService } from './../../../modulos/usuario/services/usuario.service';
import { Environment } from 'node_modules/ng-environmenter/lib/environmenter/environment.d';
import { Component, EventEmitter, Inject, Input, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Authentication, LoginSuccessComponent } from '@nuvem/angular-base';
import { Usuario } from 'src/app/dominios/usuario';
import { Chave } from './../../../dominios/chave';
import { ENVIRONMENTER } from 'ng-environmenter';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent extends LoginSuccessComponent implements OnInit {

  display: boolean = false;
  loginUsuario: FormGroup;
  @Input() usuario = new Usuario();
  chaveInput: string;
  chave = new Chave();
  @Output() emitUsuario = new EventEmitter<Usuario>();

  constructor(
    private fb: FormBuilder,
    @Inject(ENVIRONMENTER) public environment: any,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute,
    private authentication: Authentication<Usuario>,
  ) {
    super(authentication);
  }
  
  ngOnInit(): void {
    this.loginUsuario = this.fb.group({
      chave: ['', Validators.required]
    });
  }

  pegarUsuarioLocalStorage(){
    const usuario = JSON.parse(window.localStorage.getItem("usuario"));
    this.emitUsuario.emit(usuario);
  }

  login(chaveInput: string){
    this.chave.chave = chaveInput
    this.usuarioService.buscarUsuarioPorChave(this.chave)
      .subscribe((user: Usuario) => {
        this.emitUsuario.emit(this.usuario);
        localStorage.setItem("usuario", JSON.stringify(user));
        //precisa ver variaveis de environment
        if(this.authentication.isAuthenticated){
          this.authentication.login();
          this.environment.ActivatedRoute;
          this.authentication.redirect();
        }
        //this.authentication.redirect();

      });
  }

  mostrarDialog(){
    this.display = true;
  }

  logout(){
    localStorage.clear();
    location.reload();
  }

}
