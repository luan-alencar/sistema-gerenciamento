import { UsuarioService } from '../services/usuario.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from 'src/app/shared/components/login/login.component';
import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import {CardComponent} from './components/card/card.component';
import { ConfirmationService } from 'primeng';

@NgModule({
    declarations: [
        CardComponent,
        LoginComponent
    ], 
    imports: [
        PRIMENG_IMPORTS,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [
        ConfirmationService,
        UsuarioService
    ],
    exports: [
        PRIMENG_IMPORTS,
        CardComponent,
        LoginComponent
    ]
})
export class SharedModule { }
