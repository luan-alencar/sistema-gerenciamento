import { Component, EventEmitter, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/dominios/usuario';
import { Chave } from './../../../dominios/chave';
import { UsuarioService } from './../../../modulos/usuario/services/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  //styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

  display: boolean = false;
  formLoginUsuario: FormGroup;
  chaveInput: string = '';
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
    this.router.navigate(['/usuarios'])
    this.chave.chave = chaveInput
    this.usuarioService.buscarUsuarioPorChave(this.chave)
      .subscribe((usuario: Usuario) => {
        this.emitUsuario.emit(usuario);
        localStorage.setItem("usuario", JSON.stringify(usuario));
      });
  }

  usuarioAdministrador(chave: string) {
    this.chave.chave = chave;
    this.usuarioService.buscarUsuarioPorChave(this.chave)
      .subscribe((user: Usuario) => {
        if (chave === 'admin' && user.tipoUsuario === 'a') {
          this.emitUsuario.emit(user);
          localStorage.setItem("admin", JSON.stringify(user));
        }
      });
  }

  mostrarDialog() {
    this.display = true;
  }

  logout() {
    localStorage.clear();
    location.reload();
  }

}