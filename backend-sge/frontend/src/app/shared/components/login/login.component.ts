import { Chave } from './../../../dominios/chave';
import { LoginService } from './../../../services/login.service';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from '../../../services/usuario.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Usuario } from 'src/app/dominios/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  display: boolean = false;
  loginUsuario: FormGroup;
  @Input() usuario = new Usuario();
  ChaveInput: string;
  chave = new Chave();
  @Output() emitUsuario = new EventEmitter<Usuario>();

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private loginService: LoginService,
    private route: ActivatedRoute
  ) {}

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
    this.loginService.login(this.chave)
      .subscribe((user: Usuario) => {
        this.emitUsuario.emit(this.usuario);
        localStorage.setItem("usuario", JSON.stringify(user));
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
