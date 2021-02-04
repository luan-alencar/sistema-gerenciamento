import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
//import { PerguntasService } from './../perguntas/services/perguntas.service';
import { EventoService } from 'src/app/modulos/evento/services/evento.service';
import { EventoListagemComponent } from './components/evento-listagem/evento-listagem.component';
import { EventoFormularioComponent } from './components/evento-formulario/evento-formulario.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventoRoutingModule } from './evento-routing.module';



@NgModule({
  declarations: [
    EventoFormularioComponent,
    EventoListagemComponent
  ],
  providers:[
    EventoService,
    //PerguntasService
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    EventoRoutingModule,
    SharedModule,
    HttpClientModule,
    EventoRoutingModule
  ]
})
export class EventoModule { }
