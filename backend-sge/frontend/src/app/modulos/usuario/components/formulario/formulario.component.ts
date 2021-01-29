import { UsuarioService } from 'src/app/modulos/usuario/services/usuario.service';
import { Usuario } from './../../../../dominios/usuario';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  formUsuario: FormGroup;
  usuario = new Usuario();
  edicao = false;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      if(params.id){
        this.edicao = true;
      }
    });

    this.formUsuario = this.fb.group({
      
      nome: ['', Validators.nullValidator], //pode ser iniciado aqui e ser editado na pagina
      cpf: '',
      email: '',
      telefone: '',
      dataNascimento: '',
    });
  }

  buscarUsuario(id: number){
    this.usuarioService.buscarUsuarioPorId(id)
      .subscribe(usario => {

      });
  }

  salvar(){
    if(this.formUsuario.invalid){
      alert('formulario invalido')
      return;
    }

    if(this.edicao){
      this.usuarioService.editarUsuario(this.usuario)
      .subscribe(usuario => {
        console.log("usuario salvo", usuario);
        alert('Usuario salvo')
      }, (erro: HttpErrorResponse) => {
        alert(erro.error.message);
      });
    }else{
      this.usuarioService.salvarUsuario(this.usuario)
      .subscribe(usuario => {
        console.log("usuario salvo", usuario);
        alert('Usuario salvo')
      }, (erro: HttpErrorResponse) => {
        alert(erro.error.message);
      });
    }

  }
}
