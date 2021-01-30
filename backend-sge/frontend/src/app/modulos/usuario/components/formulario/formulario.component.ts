import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { UsuarioService } from 'src/app/modulos/usuario/services/usuario.service';
import { Usuario } from './../../../../dominios/usuario';
import { Component, EventEmitter, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { ConfirmationService } from 'primeng';
import {InputMaskModule} from 'primeng/inputmask';
import { Input } from '@angular/core';
import { Output } from '@angular/core';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  @Input() usuario = new Usuario();
  @Input() edicao = false;
  @Output() usuarioSalvo = new EventEmitter<Usuario>();

  cadastroUsuario: FormGroup;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      if(params.id){
        this.edicao = true;
        this.buscarUsuario(params.id);
      }
    });

    this.cadastroUsuario = this.fb.group({
      nome: ['', Validators.nullValidator], //pode ser iniciado aqui e ser editado na pagina
      cpf: '',
      email: '',
      telefone: '',
      dataNascimento: '',
    });
  }

  buscarUsuario(id: number){
    this.usuarioService.buscarUsuarioPorId(id)
      .subscribe(usuario => {
        this.usuario = usuario;
      });
  }

  salvar(){
    if(this.cadastroUsuario.invalid){
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

  fecharDialog(usuarioSalvo: Usuario) {
    this.usuarioSalvo.emit(usuarioSalvo);
  }

}
