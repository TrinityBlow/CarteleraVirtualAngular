import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publicacion } from '_models/publicacion';
import { environment as env } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PublicacionService {

  url = `${env.url}/publicaciones`;

  constructor(private http: HttpClient) { }
  

  getPublicacion(publicacionId):Observable<Publicacion>{
    return this.http.get<Publicacion>(`${this.url}/${publicacionId}`);
  }      

  getPublicaciones():Observable<Publicacion[]>{
    return this.http.get<Publicacion[]>(this.url);
  }      

  createPublicacion(formPublicacion,carteleraIdP, usernameEmail){
    return this.http.post(`${this.url}`,{
      titulo: formPublicacion.tituloP.value,
      creador: usernameEmail,
      carteleraId: carteleraIdP
    });
  }

  deletePublicacion(publicacion){
    return this.http.delete(`${this.url}/${publicacion.id}`);
  }
  
  toggleComentarios(element:Publicacion):Observable<Publicacion> {
    return this.http.put<Publicacion>(`${this.url}/toggleComentarios/${element.id}`,null);
  }
}
