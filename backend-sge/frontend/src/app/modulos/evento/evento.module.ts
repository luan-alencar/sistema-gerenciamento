import { EventoListagemComponent } from './components/evento-listagem/evento-listagem.component';
import { EventoFormularioComponent } from './components/evento-formulario/evento-formulario.component';
import { EventoService } from './services/evento.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from 'src/app/shared/shared.module';
import { PerguntasService } from '../perguntas/services/perguntas.service';


@NgModule({
  declarations: [
    EventoFormularioComponent,
    EventoListagemComponent
  ],
  providers: [
    EventoService,
    PerguntasService
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    EventoRoutingModule,
    SharedModule,
    HttpClientModule
  ]
})
export class EventoModule { }
