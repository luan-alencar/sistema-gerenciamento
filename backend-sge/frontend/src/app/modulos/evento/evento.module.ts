import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { EventoFormularioComponent } from './evento-formulario/evento-formulario.component';
import { EventoListagemComponent } from './evento-listagem/evento-listagem.component';


@NgModule({
  declarations: [
    EventoFormularioComponent,
    EventoListagemComponent
  ],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule
  ]
})

export class EventoModule { }
