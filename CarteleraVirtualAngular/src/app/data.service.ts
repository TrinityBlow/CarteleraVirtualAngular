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

  getBody(){
    return 3;
  }
  
  getCartelerasCant() {
    let url = `${env.url}/carteleras/cantidad`;
    let body = this.getBody();

    return this.http.post(url,body);


  }

  getCategorias() {
    let url = `${env.url}/categorias`;

    return this.http.get(url);


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

  createCategoria(formC){
    let url = `${env.url}/categorias`;
    return this.http.post(url,{
      tipo: formC.tipoC.value
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

    return this.http.get(url);

  }

  

  deleteCartelera(cartelera){
    
    let url = `${env.url}/carteleras/${cartelera.id}`;
    return this.http.delete(url);
    
  }

  deleteCarteleraTitulo(cartelera){
    let url = `${env.url}/carteleras`;
    
    return this.http.patch(url,{
      titulo: cartelera.titulo
    });
    
  }
    //  return this.http.get('http://localhost:8080/CarteleraVirtual/carteleras')
}
