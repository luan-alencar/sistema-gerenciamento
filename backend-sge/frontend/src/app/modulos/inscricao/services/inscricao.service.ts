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

  encontrarInscricaoPorId(id: number): Observable<Inscricao> {
    return this.http.get<Inscricao>(`${this.url}/${id}`);
  }

  buscarTodasInscricoes(): Observable<Inscricao[]> {
    return this.http.get<Inscricao[]>(`${this.url}`);
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
}