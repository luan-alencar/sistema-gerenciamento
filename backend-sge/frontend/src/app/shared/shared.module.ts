import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from 'src/app/shared/components/login/login.component';
import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import {CardComponent} from './components/card/card.component';
import { ConfirmationService } from 'primeng';
//import { AUTH_CONFIG } from '@nuvem/angular-base';
import { FormularioComponent } from './components/formulario/formulario.component';

@NgModule({
    declarations: [
        CardComponent,
        LoginComponent,
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
        LoginComponent,
        FormularioComponent
    ]
})
export class SharedModule { }
