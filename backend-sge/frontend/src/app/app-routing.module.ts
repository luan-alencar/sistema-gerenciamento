import { EventoModule } from './modulos/evento/evento.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { UsuarioModule } from './modulos/usuario/usuario.module';
import { LoginComponent } from './modulos/usuario/login/login.component';

const routes: Routes = [
    
    { 
    path: 'usuarios',
    loadChildren: () => UsuarioModule, 
    },
    {
      path: 'login',
      component: LoginComponent,
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