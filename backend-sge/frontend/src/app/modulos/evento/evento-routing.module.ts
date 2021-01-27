import { ListagemComponent } from './../usuario/components/listagem/listagem.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from '../usuario/components/formulario/formulario.component';


const routes: Routes = [
{
  path: 'listagem',
  component: ListagemComponent
},
{
  path: 'formulario',
  component: FormularioComponent
}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
