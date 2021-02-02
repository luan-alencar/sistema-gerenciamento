import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from './../../../modulos/usuario/services/usuario.service';
import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Usuario } from 'src/app/dominios/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUsuario: FormGroup;
  @Input() usuario = new Usuario();

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loginUsuario = this.fb.group({
      chave: ['', Validators.required]
    });
  }

  login(){

  }

}
