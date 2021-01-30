import { EventoPergunta } from "./evento-pergunta";

export class Evento {

    id: number;
    local: string;
    descricao: string;
    titulo: string;
    qtdVagas: number;
    valor: number;
    dataInicio: Date;
    dataFim: Date;
    tipoInscricao: boolean;
    idTipoEvento: number;
    perguntas: EventoPergunta[] = [];

    constructor() { }
}