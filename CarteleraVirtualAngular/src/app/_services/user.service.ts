import { Injectable }   from '@angular/core';
import { HttpClient }   from '@angular/common/http';
import { Observable }   from 'rxjs';
import { SimpleUser }   from '../_models/SimpleUser';
import { environment as env } from '../../environments/environment';


@Injectable()
export class UserService {
  url = `${env.url}/usuarios`;

  constructor(private http: HttpClient) { }

  getUsersOwners(){
    return this.http.get<SimpleUser[]>(`${this.url}/owners`);
  }


  getUsers():Observable<SimpleUser[]>{
    return this.http.get<SimpleUser[]>(`${this.url}`);
  }      

}