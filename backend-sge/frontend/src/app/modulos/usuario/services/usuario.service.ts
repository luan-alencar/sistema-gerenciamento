import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from 'src/app/dominios/usuario';
import { environment } from 'src/environments/environment';

@Injectable()
export class UsuarioService {

  url = `${environment.apiUrl}/usuarios`;

  constructor(private http: HttpClient) { }

  buscarUsuarioPorId(id: number): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.url}/${id}`);
  }

  buscarUsuarioPorEmail(email: string): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.url}/email/${email}`);
  }

  getUsuarios(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(`${this.url}`);
  }

  salvarUsuario(usuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(this.url, usuario);
  }

  editarUsuario(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(this.url, usuario);
  }

  deletarUsuario(id: number): Observable<Usuario>{
    return this.http.delete<Usuario>(`${this.url}/${id}`);
  }

  loginSucesso(): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.url}/login`);
  }

}
