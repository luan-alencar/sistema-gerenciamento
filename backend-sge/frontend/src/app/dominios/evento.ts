import { EventoPergunta } from "./evento-pergunta";
import { Pergunta } from "./pergunta";
import { TipoEvento } from "./tipo-evento";
import { TipoSituacao } from './tipo-situacao';

export class Evento {

    id: number;
    titulo: string;
    local: string;
    descricao: string;
    qtdVagas: number;
    valor: number;
    dataInicio: Date;
    dataFim: Date;
    tipoInscricao: boolean;
    tipoEvento: number;
    perguntas: EventoPergunta[] = [];

    constructor() { }
}