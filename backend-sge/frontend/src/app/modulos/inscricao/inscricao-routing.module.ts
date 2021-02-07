import { InscricaoListagemComponent } from './components/inscricao-listagem/inscricao-listagem.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InscricaoFormularioComponent } from './components/inscricao-formulario/inscricao-formulario.component';


const routes: Routes = [
  {
    path: 'listagem',
    component: InscricaoListagemComponent
  },
  {
    path: 'eventos/inscricoes',
    component: InscricaoFormularioComponent
  },
  {
    path: 'listagem/:id',
    component: InscricaoListagemComponent
  },
  {
    path: 'formulario/:id',
    component: InscricaoFormularioComponent
  }
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InscricaoRoutingModule { }
