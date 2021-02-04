import { EventoFormularioComponent } from './components/evento-formulario/evento-formulario.component';
import { EventoListagemComponent } from './components/evento-listagem/evento-listagem.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

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
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
