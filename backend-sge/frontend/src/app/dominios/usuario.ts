import { User } from '@nuvem/angular-base';

export class Usuario implements User{
    
    id: number;
    nome: string;
    cpf: string;
    email: string;
    telefone: string;
    dataNascimento: Date;
    tipoUsuario: string;
    roles: string[];
    name: string;

    constructor(){
        this.roles = ["u", "a"];
    }

}