import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../service/usuario.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
})
export class ListagemComponent implements OnInit {

  usuarios: string[] = [];

  constructor(
    public servico: UsuarioService
  ) { }

  ngOnInit(): void {
    this.servico.getUsuarios()
    .subscribe((usuarios: string[]) => {
      console.log('subscribe');
    }); 

  }

}
