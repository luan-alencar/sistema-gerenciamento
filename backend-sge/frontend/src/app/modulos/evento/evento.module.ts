import { EventoRoutingModule } from './evento-routing.module';
import { FormularioComponent } from './formulario/formulario.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';



@NgModule({
  declarations: [FormularioComponent],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule
  ]

})
export class EventoModule { }
