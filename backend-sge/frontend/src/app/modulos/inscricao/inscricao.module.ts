import { EventoService } from './../evento/services/evento.service';
import { PerguntasService } from './../perguntas/services/perguntas.service';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { InscricaoFormularioComponent } from './components/inscricao-formulario/inscricao-formulario.component';
import { InscricaoListagemComponent } from './components/inscricao-listagem/inscricao-listagem.component';
import { InscricaoRoutingModule } from './inscricao-routing.module';
import { InscricaoService } from './services/inscricao.service';
import { DialogInscricaoFormularioRespostaComponent } from './components/inscricao-formulario/components/dialog-inscricao-formulario-resposta/dialog-inscricao-formulario-resposta.component';



@NgModule({
  declarations: [InscricaoFormularioComponent, InscricaoListagemComponent, DialogInscricaoFormularioRespostaComponent],
  providers: [
    InscricaoService,
    PerguntasService,
    EventoService
  ],
  imports: [
    CommonModule,
    InscricaoRoutingModule
  ]
})
export class InscricaoModule { }
