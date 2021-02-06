import { ListagemComponent } from './modulos/usuario/components/listagem/listagem.component';
import { InscricaoModule } from './modulos/inscricao/inscricao.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { UsuarioModule } from './modulos/usuario/usuario.module';
import { EventoModule } from './modulos/evento/evento.module';
//import { InscricaoModule } from './modulos/inscricao/inscricao.module';
import { UsuarioModule } from './modulos/usuario/usuario.module';

const routes: Routes = [
    {
    path: '',
    component:ListagemComponent
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
      path: 'inscricao',
      loadChildren: () => InscricaoModule, 
    },
      
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    loadChildren: () => UsuarioModule,
  },

  {
    path:'inscricoes',
    loadChildren: () => InscricaoModule
  },  

  { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros' } }

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }