import { EventoPergunta } from "./evento-pergunta";

<<<<<<< HEAD
export class Evento{
  
=======
export class Evento {

>>>>>>> 089d6f124418304216683073ac78e3af0dc4b49c
    id: number;
    local: string;
    titulo: string;
    descricao: string;
    qtdVagas: number;
    valor: number;
    dataInicio: Date;
    dataFim: Date;
    tipoInscricao: boolean;
    idTipoEvento: number;
    perguntas: EventoPergunta[] = [];

    constructor() { }
}