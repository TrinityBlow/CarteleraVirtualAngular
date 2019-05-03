import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment as env } from '../../environments/environment';

import { Cartelera } from '../_models/Cartelera';
import { Publicacion } from '../_models/Publicacion';
import { SimpleUser } from '../_models/SimpleUser';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CarteleraService {


    url = `${env.url}/carteleras`;
    carteleraSelected:Cartelera = null;

    constructor(private http: HttpClient) { }

    getCarteleras(): Observable<Cartelera[]> {
        return this.http.get<Cartelera[]>(this.url);
    }

    getAll() {
        return this.http.get<Cartelera[]>(this.url);
    }

    getSelectedCartelera():Cartelera {
        return this.carteleraSelected;
    }

    selectCartelera(cartelera:Cartelera){
        this.carteleraSelected = cartelera;
    }

    getById(carteleraId){
        return this.http.get<Cartelera>(`${this.url}/${carteleraId}`);
    }

    modificarCartelera(carteleraModificarForm){
        return this.http.put<Cartelera[]>(`${this.url}/${carteleraModificarForm.id.value}`,
            {
                titulo:  carteleraModificarForm.titulo.value,
                categoria: carteleraModificarForm.categoria.value
            }
        );
    }

    newEmptyCartelera():Cartelera{
        return {
            id: -1, 
            titulo: "", 
            creador: "", 
            fecha: -1,
            categoria: {
                id:-1,
                tipo:""
            },
            publicaciones:[
                
            ]
        };
    }

    getPublicacionesCartelera(cartelera:Cartelera):Observable<Publicacion[]>{
        return this.http.get<Publicacion[]>(`${this.url}/publicaciones/${cartelera.id}`);
    }

    getCarteleraById(carteleraId):Observable<Cartelera>{
        return this.http.get<Cartelera>(`${this.url}/${carteleraId}`);
    }

    getPublicacionesCarteleraId(carteleraId){
        return this.http.get<Publicacion[]>(`${this.url}/publicaciones/${carteleraId}`);
    }

    getOwnersCartelera(carteleraId):Observable<SimpleUser[]>{
        return this.http.get<SimpleUser[]>(`${this.url}/owners/${carteleraId}`);
    }

    nuevosOwnersCartelera(carteleraId, nuevosOwnersEmail):Observable<Cartelera>{
        
        return this.http.put<Cartelera>(`${this.url}/owners/${carteleraId}`,
            nuevosOwnersEmail
        );
        
    }

}