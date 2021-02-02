import { Chave } from './../dominios/chave';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Usuario } from '../dominios/usuario';
import { TRISTATECHECKBOX_VALUE_ACCESSOR } from 'primeng';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = `${environment.apiUrl}/usuarios/login`;

  constructor(private http: HttpClient) { }

  login(chave: Chave): Observable<Usuario>{
    return this.http.post<Usuario>(this.url, chave);
  }

}
