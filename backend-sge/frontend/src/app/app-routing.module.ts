import { ListagemComponent } from './modulos/usuario/components/listagem/listagem.component';
import { EventoModule } from './modulos/evento/evento.module';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { UsuarioModule } from './modulos/usuario/usuario.module';

const routes: Routes = [
    { 
    path: '',
    component: ListagemComponent, 
    },
    { 
      path: 'usuarios',
      loadChildren: () => UsuarioModule, 
      },
    {
      path: 'eventos',
      loadChildren: () => EventoModule,
    },
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },

];

@NgModule({
imports: [
  RouterModule.forRoot(routes)
],
exports: [RouterModule]
})
export class AppRoutingModule { }