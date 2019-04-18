import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment as env } from '../../environments/environment';

import { Cartelera } from '../_models/Cartelera';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CarteleraService {


    url = `${env.url}/carteleras`;

    constructor(private http: HttpClient) { }

    getCarteleras(): Observable<Cartelera[]> {
        return this.http.get<Cartelera[]>(this.url);
        }

    getAll() {
        return this.http.get<Cartelera[]>(this.url);
    }
}