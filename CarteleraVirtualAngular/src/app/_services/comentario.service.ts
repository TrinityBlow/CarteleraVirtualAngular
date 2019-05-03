import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comentario } from '_models/Comentario';
import { environment as env } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {

  url = `${env.url}/comentarios`;

  constructor(private http: HttpClient) { }

  getComentarioDePublicacion(publicacionId):Observable<Comentario[]>{
    return this.http.get<Comentario[]>(`${this.url}/publicacion/${publicacionId}`);
  }

  responderComentario(comentarioId,respuestaC,usernameR){
    return this.http.post(`${this.url}/responder`,{
      comentarioId: comentarioId,
      respuesta: respuestaC,
      usuarioEmail: usernameR
    });

  }
  
  //buscar mejor forma de pasar los datos
  crearComentario(comentarioC:string,usernameEmail:string,publicacionId:number){
    return this.http.post(`${this.url}/comentar`,{
      comentario: comentarioC,
      username: usernameEmail,
      publicacion: publicacionId
    });
  }

}
