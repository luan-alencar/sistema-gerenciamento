import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormularioComponent } from '../../shared/components/formulario/formulario.component';
import { ListagemComponent } from './components/listagem/listagem.component';


const routes: Routes = [
  {
    path: '',
    component: ListagemComponent
  },
  {
    path:'formulario',
    component: FormularioComponent
  },
  {
    path:'formulario/:id',
    component: FormularioComponent
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }