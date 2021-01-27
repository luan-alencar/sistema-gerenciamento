import { CardComponent } from 'src/app/shared/components/card/card.component';
import { ListagemComponent } from './../usuario/components/listagem/listagem.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from './components/formulario/formulario.component';


const routes: Routes = [
{
  path: 'listagem',
  component: ListagemComponent
},
{
  path: 'formulario',
  component: FormularioComponent
},

{
  path: 'card',
  component: CardComponent
}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
