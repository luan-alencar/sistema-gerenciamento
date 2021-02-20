import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InscricaoFormularioComponent } from 'src/app/modulos/inscricao/components/inscricao-formulario/inscricao-formulario.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { EventoRoutingModule } from '../evento/evento-routing.module';
import { EventoService } from './../evento/services/evento.service';
import { PerguntasService } from './../perguntas/services/perguntas.service';
import { DialogInscricaoFormularioRespostaComponent } from './components/inscricao-formulario/components/dialog-inscricao-formulario-resposta/dialog-inscricao-formulario-resposta.component';
import { InscricaoListagemComponent } from './components/inscricao-listagem/inscricao-listagem.component';
import { InscricaoService } from './services/inscricao.service';



@NgModule({
  declarations: [InscricaoFormularioComponent, InscricaoListagemComponent, DialogInscricaoFormularioRespostaComponent],
  providers: [
    PerguntasService,
    EventoService
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    EventoRoutingModule,
    SharedModule]
})
export class InscricaoModule { }
