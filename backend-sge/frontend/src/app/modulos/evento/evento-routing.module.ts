import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventoFormularioComponent } from './components/evento-formulario/evento-formulario.component';
import { EventoListagemComponent } from './components/evento-listagem/evento-listagem.component';


const routes: Routes = [
  {
    path: 'evento-listagem',
    component: EventoListagemComponent
  },
  {
    path: 'evento-listagem/evento-formulario',
    component: EventoFormularioComponent
  },

  {
    path: 'evento-formulario/:id',
    component: EventoFormularioComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
