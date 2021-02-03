import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import {CardComponent} from './components/card/card.component';
import { ConfirmationService } from 'primeng';
import { AUTH_CONFIG } from '@nuvem/angular-base';
import { FormularioComponent } from '../modulos/usuario/components/formulario/formulario.component';

@NgModule({
    declarations: [
        CardComponent,
        FormularioComponent
    ],
    imports: [
        PRIMENG_IMPORTS,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [
        ConfirmationService
    ],
    exports: [
        PRIMENG_IMPORTS,
        CardComponent,
        // LoginComponent,
        FormularioComponent
    ]
})
export class SharedModule { }