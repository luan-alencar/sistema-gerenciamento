import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';

import { ListagemComponent } from './components/formulario/listagem.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormularioComponent } from '../evento/formulario/formulario.component';
import { UsuarioService } from './service/usuario.service';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [FormularioComponent, ListagemComponent],
  providers: [UsuarioService],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    SharedModule,
    HttpClientModule
  ]
})
export class UsuarioModule { }