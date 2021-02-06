import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import {CardComponent} from './components/card/card.component';
import { LoginComponent } from './components/login/login.component';
import { ConfirmationService } from 'primeng';
import { FormularioComponent } from './components/formulario/formulario.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AUTH_CONFIG } from '@nuvem/angular-base';
import { FormularioComponent } from '../modulos/usuario/components/formulario/formulario.component';
import { InscricaoFormularioComponent } from '../modulos/inscricao/components/inscricao-formulario/inscricao-formulario.component';

@NgModule({
    declarations: [CardComponent, LoginComponent, FormularioComponent], 
    imports: [
        PRIMENG_IMPORTS,
        ReactiveFormsModule,
        FormsModule
    ],
    providers: [ConfirmationService],
    exports: [
        PRIMENG_IMPORTS,CardComponent,LoginComponent, FormularioComponent
    ]
})
export class SharedModule { }