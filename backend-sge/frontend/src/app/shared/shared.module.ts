import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import {CardComponent} from './components/card/card.component';
import { ConfirmationService } from 'primeng';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations: [CardComponent], 
    imports: [
        PRIMENG_IMPORTS,
        ReactiveFormsModule,
        FormsModule
    ],
    providers: [ConfirmationService],
    exports: [
        PRIMENG_IMPORTS,CardComponent
    ]
})
export class SharedModule { }