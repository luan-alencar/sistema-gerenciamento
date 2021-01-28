import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';

import { SharedModule } from 'src/app/shared/shared.module';
import { UsuarioService } from './service/usuario.service';
import { HttpClientModule } from '@angular/common/http';
import { FormularioComponent } from './components/formulario/formulario.component';
import { ListagemComponent } from './components/listagem/listagem.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [ListagemComponent, FormularioComponent],
  providers: [UsuarioService],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    UsuarioRoutingModule,
    SharedModule,
    HttpClientModule
  ]
})
export class UsuarioModule { }