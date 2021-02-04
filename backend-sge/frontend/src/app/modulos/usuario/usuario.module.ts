import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UsuarioService } from 'src/app/modulos/usuario/services/usuario.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { ListagemComponent } from './components/listagem/listagem.component';
import { UsuarioRoutingModule } from './usuario-routing.module';

@NgModule({
  declarations: [ListagemComponent],
  providers: [UsuarioService],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    SharedModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class UsuarioModule { }