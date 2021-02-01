import { EventoPergunta } from "./evento-pergunta";
import { TipoSituacao } from './tipo-situacao';

export class Evento {

    id: number;
    local: string;
    descricao: string;
    titulo: string;
    qtdVagas: number;
    valor: number;
    dataInicio: Date;
    dataFim: Date;
    tipoInscricao: TipoSituacao;
    tipoEvento: number;
    perguntas: EventoPergunta[] = [];

    constructor() { }
}