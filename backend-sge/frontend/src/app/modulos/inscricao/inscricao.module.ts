import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { InscricaoFormularioComponent } from 'src/app/modulos/inscricao/components/inscricao-formulario/inscricao-formulario.component';
import { EventoService } from './../evento/services/evento.service';
import { PerguntasService } from './../perguntas/services/perguntas.service';
import { DialogInscricaoFormularioRespostaComponent } from './components/inscricao-formulario/components/dialog-inscricao-formulario-resposta/dialog-inscricao-formulario-resposta.component';
import { InscricaoListagemComponent } from './components/inscricao-listagem/inscricao-listagem.component';
import { InscricaoRoutingModule } from './inscricao-routing.module';
import { InscricaoService } from './services/inscricao.service';



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
