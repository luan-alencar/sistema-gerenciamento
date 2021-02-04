import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pergunta } from 'src/app/dominios/pergunta';
import { environment } from 'src/environments/environment';

@Injectable()
export class PerguntasService {

  url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  buscarTodasPerguntas(): Observable<Pergunta[]> {
    return this.http.get<Pergunta[]>(`${this.url}/perguntas`);
  }

  salvarPergunta(pergunta: Pergunta): Observable<Pergunta> {
    return this.http.post<Pergunta>(`${this.url}/perguntas`, pergunta)
  }
}
