import { Injectable }   from '@angular/core';
import { HttpClient }   from '@angular/common/http';
import { Observable }   from 'rxjs';

import { UserGit } from '_models/userGit';

@Injectable()
export class UserService {
  private serviceUrl = 'https://jsonplaceholder.typicode.com/users';

  constructor(private http: HttpClient) { }

  getUser(): Observable<UserGit[]> {
    return this.http.get<UserGit[]>(this.serviceUrl);
  }

}