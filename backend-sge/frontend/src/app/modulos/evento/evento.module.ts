import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ListagemComponent } from '../usuario/components/listagem/listagem.component';
import { CardComponent } from 'src/app/shared/components/card/card.component';
import { FormularioComponent } from '../usuario/components/formulario/formulario.component';


@NgModule({
  declarations: [ListagemComponent, FormularioComponent],
  imports: [
    CommonModule,
    EventoRoutingModule,
    // CardComponent,
    // ListagemComponent,
    // FormularioComponent,
    SharedModule
  ]
})
export class EventoModule { }
