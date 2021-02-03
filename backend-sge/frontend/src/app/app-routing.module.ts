import { UsuarioModule } from './modulos/usuario/usuario.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { EventoModule } from './modulos/evento/evento.module';

const routes: Routes = [

  {
    path: 'eventos',
    loadChildren: () => EventoModule,
  },
  {
    path: 'usuarios',
    loadChildren: () => UsuarioModule,
  },
  { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} }

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }