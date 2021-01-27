import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { FormularioComponent } from './formulario/formulario.component';
import { ListagemComponent } from './listagem/listagem.component';
import { SharedModule } from '../../shared/shared.module';


@NgModule({
  declarations: [FormularioComponent, ListagemComponent],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule
  ]
})

export class EventoModule { }
