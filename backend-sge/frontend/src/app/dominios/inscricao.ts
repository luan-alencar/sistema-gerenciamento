import { InscricaoResposta } from "./inscricao-resposta";

export class Inscricao {

    id: number;
    idUsuario: number;
    idEvento: number;
    idTipoSituacao: number;
    resposta: InscricaoResposta[] = [];

    constructor(){}
}