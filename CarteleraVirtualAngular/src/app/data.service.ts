import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of,Observable, pipe, throwError } from 'rxjs';
import { map, filter, catchError, mergeMap, take } from 'rxjs/operators';
import { environment as env } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataService {


  constructor(private http: HttpClient) { }

  getHeaders(){
    return {'token':'2123456'};
  }

  getBody(){
    return 3;
  }
  
  getCartelerasCant() {
    let url = `${env.url}/carteleras/cantidad`;
    let headers = this.getHeaders();
    let body = this.getBody();

    return this.http.post(url,body,{
      headers: headers
    });


  }

  getCategorias() {
    let url = `${env.url}/categorias`;
    let headers = this.getHeaders();

    return this.http.get(url,{
      headers: headers
    });


  }

  createUser(formC){
    let url = `${env.url}/usuarios`;
    return this.http.post(url,{
      nombre: formC.nombre.value,
      pass: formC.passwordC.value,
      apellido: formC.apellido.value,
      email: formC.emailC.value,
      rol: formC.rol.value
    });
  }

  createCartelera(formC,adminEmail){
    let url = `${env.url}/carteleras`;
    return this.http.post(url,{
      titulo: formC.titulo.value,
      categoria: formC.categoria.value,
      admin: adminEmail
    });
  }

  getCarteleras() {
    let url = `${env.url}/carteleras`;
    let headers = this.getHeaders();

    return this.http.get(url,{
      headers: headers
    });

  }
    //  return this.http.get('http://localhost:8080/CarteleraVirtual/carteleras')
}
