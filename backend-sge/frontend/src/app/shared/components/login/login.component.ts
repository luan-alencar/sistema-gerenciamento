import { Router } from '@angular/router';
import { UsuarioService } from './../../../modulos/usuario/services/usuario.service';
import { Component, EventEmitter, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuario } from 'src/app/dominios/usuario';
import { Chave } from './../../../dominios/chave';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  //styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

  display: boolean = false;
  formLoginUsuario: FormGroup;
  chaveInput: string;
  chave = new Chave();
  @Output() emitUsuario = new EventEmitter<Usuario>();

  constructor(
    private fb: FormBuilder,
    public usuarioService: UsuarioService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.pegarUsuarioLocalStorage();
    this.formLoginUsuario = this.fb.group({
      chave: ['', Validators.required]
    });
  }

  pegarUsuarioLocalStorage() {
    const usuario = JSON.parse(window.localStorage.getItem("usuario"));
    this.emitUsuario.emit(usuario);
  }

  login(chaveInput: string) {
    this.router.navigate(['/eventos/listagem'])
    this.chave.chave = chaveInput
    this.usuarioService.buscarUsuarioPorChave(this.chave)
      .subscribe((usuario: Usuario) => {
        this.emitUsuario.emit(usuario);
        localStorage.setItem("usuario", JSON.stringify(usuario));
      });
  }

  mostrarDialog() {
    this.display = true;
  }

  fecharDialog() {
    this.display = false;
  }

  logout() {
    localStorage.clear();
    location.reload();
  }

}
