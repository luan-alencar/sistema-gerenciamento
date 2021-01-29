import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormularioComponent } from './components/formulario/formulario.component';
import { ListagemComponent } from './components/listagem/listagem.component';
import { UsuarioService } from './services/usuario.service';
import { UsuarioRoutingModule } from './usuario-routing.module';



@NgModule({
  declarations: [
    FormularioComponent,
    ListagemComponent
  ],
  providers: [
    UsuarioService
  ],
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
