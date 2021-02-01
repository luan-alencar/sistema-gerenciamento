import { TipoSituacao } from './tipo-situacao';
import { TipoEvento } from './tipo-evento';
import { EventoPergunta } from "./evento-pergunta";
import { SelectItem } from 'primeng';

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