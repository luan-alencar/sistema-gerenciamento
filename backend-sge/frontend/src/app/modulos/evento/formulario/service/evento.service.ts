import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Evento } from 'src/app/dominios/evento';

@Injectable()
export class EventoService {

  url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];

  nomesEvento: string[] = [
    "Bamboo Watch",
    "Black Watch",
    "Blue Band",
    "Blue T-Shirt",
    "Bracelet",
    "Brown Purse",
    "Chakra Bracelet",
    "Galaxy Earrings",
    "Game Controller",
    "Gaming Set",
    "Gold Phone Case",
    "Green Earbuds",
    "Green T-Shirt",
    "Grey T-Shirt",
    "Headphones",
    "Light Green T-Shirt",
    "Lime Band",
    "Mini Speakers",
    "Painted Phone Case",
    "Pink Band",
    "Pink Purse",
    "Purple Band",
    "Purple Gemstone Necklace",
    "Purple T-Shirt",
    "Shoes",
    "Sneakers",
    "Teal T-Shirt",
    "Yellow Earbuds",
    "Yoga Mat",
    "Yoga Set",
  ];

  getEventosSmall() {
    return this.http.get<any>('assets/Eventos-small.json')
      .toPromise()
      .then(res => <Evento[]>res.data)
      .then(data => { return data; });
  }

  getEventos() {
    return this.http.get<any>('assets/Eventos.json')
      .toPromise()
      .then(res => <Evento[]>res.data)
      .then(data => { return data; });
  }


}
