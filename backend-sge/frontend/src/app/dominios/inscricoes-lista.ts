import { InscricaoResposta } from './inscricao-resposta';
import { Pergunta } from 'src/app/dominios/pergunta';

export class InscricoesLista {
    id: number
    nomeUsuario: string
    emailUsuario: string
    situacaoDescricao:string
    idSituacao:number
    perguntas: Pergunta[] = []
    inscricoesResposta: InscricaoResposta[] = []
}