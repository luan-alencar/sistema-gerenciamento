import { EventoListagemComponent } from './modulos/evento/components/evento-listagem/evento-listagem.component';
import { InscricaoModule } from './modulos/inscricao/inscricao.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { UsuarioModule } from './modulos/usuario/usuario.module';
import { EventoModule } from './modulos/evento/evento.module';

const routes: Routes = [
  {
    path: 'listagem',
    component: EventoListagemComponent
  },
  {
    path: 'usuarios',
    loadChildren: () => UsuarioModule,
  },
  {
    path: 'eventos',
    loadChildren: () => EventoModule,
  },
  {
    path: 'inscricoes',
    loadChildren: () => InscricaoModule,
  },

  { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros' } },
  { path: 'login-success', component: LoginSuccessComponent }

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }