import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { map, filter, catchError, mergeMap, take } from 'rxjs/operators';


import { AuthenticationService } from '../_services';
import { User } from '../_models';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  carteleras$: Object;
  currentUser: User;

  constructor(
      private data: DataService,
      private authenticationService: AuthenticationService
  ) {
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }
  


  ngOnInit() {
    let x = 0;

    this.data.getCartelerasCant()
    .subscribe(
      data => this.carteleras$ =  data
    )
    
  }

}
