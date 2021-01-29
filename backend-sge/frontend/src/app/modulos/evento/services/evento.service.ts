import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Evento } from 'src/app/dominios/evento';
import { environment } from 'src/environments/environment';

@Injectable()
export class EventoService {

  url = `${environment.apiUrl}/eventos`;

  constructor(private http: HttpClient) { }

  buscarEventoPorId(id: number): Observable<Evento> {
    return this.http.get<Evento>(this.url);
  }

  getEventos(): Observable<Evento[]> {
    return this.http.get<Evento[]>(this.url);
  }

  salvarEvento(evento: Evento): Observable<Evento> {
    return this.http.post<Evento>(this.url, evento);
  }

  editarUsuario(evento: Evento): Observable<Evento> {
    return this.http.put<Evento>(this.url, evento);
  }

  deletarEvento(id: number): Observable<Evento> {
    return this.http.delete<Evento>(`${this.url}/${id}`)
  }
}