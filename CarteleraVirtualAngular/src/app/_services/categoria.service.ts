import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '_models/Categoria';
import { environment as env } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {


  url = `${env.url}/categorias`;

  constructor(private http: HttpClient) { }

  getCategorias(): Observable<Categoria[]> {
      return this.http.get<Categoria[]>(this.url);
      }

  deleteCategoria(categoria){
    return this.http.delete(`${this.url}/${categoria.id}`);
  }
      

      
}
