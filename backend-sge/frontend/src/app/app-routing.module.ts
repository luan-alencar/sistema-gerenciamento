import { InscricaoModule } from './modulos/inscricao/inscricao.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { EventoModule } from './modulos/evento/evento.module';
import { UsuarioModule } from './modulos/usuario/usuario.module';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { ListagemComponent } from './modulos/usuario/components/listagem/listagem.component';

const routes: Routes = [

  {
    path: '',
    component:ListagemComponent
    },
  {
    path: 'eventos',
    loadChildren: () => EventoModule,
  },
  {
    path: 'usuarios',
    loadChildren: () => UsuarioModule,
  },

  {
    path: 'inscricoes',
    loadChildren: () => InscricaoModule
  },

  { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros' } },
  { path: 'login-success', component: LoginSuccessComponent },


];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }