import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InscricaoRoutingModule } from './inscricao-routing.module';
import { InscricaoFormularioComponent } from 'src/app/modulos/inscricao/components/inscricao-formulario/inscricao-formulario.component';
import { InscricaoListagemComponent } from './components/inscricao-listagem/inscricao-listagem.component';


@NgModule({
  declarations: [InscricaoFormularioComponent, InscricaoListagemComponent],
  imports: [
    CommonModule,
    InscricaoRoutingModule
  ]
})
export class InscricaoModule { }
