import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { EventoFormularioComponent } from './evento-formulario/evento-formulario.component';
import { EventoListagemComponent } from './evento-listagem/evento-listagem.component';
import { EventoRoutingModule } from './evento-routing.module';
import { PerguntasCadastroComponent } from './perguntas-cadastro/perguntas-cadastro.component';
import { EventoService } from './services/evento.service';



@NgModule({
  declarations: [
    EventoFormularioComponent,
    EventoListagemComponent,
    PerguntasCadastroComponent
  ],
  providers: [
    EventoService
  ],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    HttpClientModule
  ]
})

export class EventoModule { }
