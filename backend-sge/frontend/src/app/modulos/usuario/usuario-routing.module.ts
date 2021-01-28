import { ListagemComponent } from './listagem/listagem.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormularioComponent } from './formulario/formulario.component';



const routes: Routes = [

  {
    path: '',
    component: ListagemComponent
  },

  {
    path: 'formulario',
    component: FormularioComponent
  },

  {
    path: 'formulario/:id',
    component: FormularioComponent
  }

];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
