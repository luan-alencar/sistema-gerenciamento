import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventoFormularioComponent } from './evento-formulario/evento-formulario.component';
import { EventoListagemComponent } from './evento-listagem/evento-listagem.component';
import { PerguntasCadastroComponent } from './perguntas-cadastro/perguntas-cadastro.component';

const routes: Routes = [

  {
    path: '',
    component: EventoListagemComponent
  },
  {
    path: 'evento-formulario',
    component: EventoFormularioComponent
  },
  {
    path: 'evento-formulario/:id',
    component: EventoFormularioComponent
  },
  {
    path: 'evento-formularios/cadastro-perguntas',
    component: PerguntasCadastroComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
