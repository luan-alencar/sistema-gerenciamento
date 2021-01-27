import { Observable, of } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class UsuarioService {

  constructor() { }

  getUsuarios(): Observable<string[]> {
    return of(this.mockUsuarios());
  }

  private mockUsuarios: Observable<string[]> {
  const usuarios: string[] = [];
  setTimeout((usuarios) => {
    usuarios = ['Luan', 'teste'];
  }, 1000);
  return of(usuarios);
}