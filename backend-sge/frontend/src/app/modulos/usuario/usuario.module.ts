import { LoginSuccessComponent } from '@nuvem/angular-base';
import { AppRoutingModule } from './../../app-routing.module';
import { ConfirmDialog, ConfirmDialogModule } from 'primeng/confirmdialog';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';

import { FormularioComponent } from './components/formulario/formulario.component';
import { ListagemComponent } from './components/listagem/listagem.component';
import { CardComponent } from '../../shared/components/card/card.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { UsuarioService } from 'src/app/modulos/usuario/services/usuario.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from '../../pages/login/login.component';


@NgModule({
  declarations: [FormularioComponent, ListagemComponent, LoginComponent],
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