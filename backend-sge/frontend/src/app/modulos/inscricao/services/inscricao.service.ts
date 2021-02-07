import { Chave } from './../../../dominios/chave';
import { InscricaoUsuario } from './../../../dominios/inscricao-usuario';
import { InscricoesLista } from './../../../dominios/inscricoes-lista';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Inscricao } from 'src/app/dominios/inscricao';

@Injectable({
  providedIn: 'root'
})
export class InscricaoService {

  url = `${environment.apiUrl}/inscricoes`;

  constructor(private http: HttpClient) { }

  buscarTodasInscricoes(): Observable<Inscricao[]> {
    return this.http.get<Inscricao[]>(`${this.url}`);
  }

  encontrarInscricaoPorId(id: number): Observable<Inscricao> {
    return this.http.get<Inscricao>(`${this.url}/${id}`);
  }

  getInscricaoPeloEventoId(id: number): Observable<InscricoesLista[]> {
    return this.http.get<InscricoesLista[]>(`${this.url}/eventoinscricoes/${id}`);
  }

  getInscricaoPeloUsuarioId(id: number): Observable<InscricaoUsuario[]> {
    return this.http.get<InscricaoUsuario[]>(`${this.url}/usuarioinscricoes/${id}`);
  }

  salvarInscricao(inscricao: Inscricao): Observable<Inscricao> {
    return this.http.post<Inscricao>(this.url, inscricao);
  }

  editarInscricao(inscricao: Inscricao): Observable<Inscricao> {
    return this.http.put<Inscricao>(this.url, inscricao);
  }

  deletarInscricao(id: number): Observable<any> {
    return this.http.delete<Inscricao>(`${this.url}/${id}`);
  }

  deletarInscricaoChave(chave: Chave): Observable<any> {
    return this.http.delete(`${this.url}/${chave}`);
  }

  getInscricaoUsuario(id : number): Observable<Inscricao[]>{
    return this.http.get<Inscricao[]>(`${this.url}/inscricoes/${id}`)
  }

}