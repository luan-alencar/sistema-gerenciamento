import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Evento } from 'src/app/dominios/evento';

@Injectable()
export class EventoService {

  url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getEventos(): Observable<Evento[]> {
    return this.http.get<Evento[]>(`${this.getEventos}/eventos`);
  }
}
