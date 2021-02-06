import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConfirmationService } from 'primeng';
import { CardComponent } from './components/card/card.component';
import { FormularioComponent } from './components/formulario/formulario.component';
import { LoginComponent } from './components/login/login.component';
import { PRIMENG_IMPORTS } from './primeng-imports';

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