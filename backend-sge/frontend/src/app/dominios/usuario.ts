export class Usuario{
    
    id: number
    nome: String;
    cpf: String;
    email: String;
    telefone: String;
    dataNascimento: Date;
    chave: string;
    tipoUsuario: string;
    roles: string[] = ["u", "a"];
    name: string;
    constructor(){}
}