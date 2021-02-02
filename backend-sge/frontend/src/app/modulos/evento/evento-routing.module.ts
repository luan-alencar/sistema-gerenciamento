import { EventoListagemComponent } from './components/evento-listagem/evento-listagem.component';
import { EventoFormularioComponent } from './components/evento-formulario/evento-formulario.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


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
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
