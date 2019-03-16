import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '_services';
import { User } from '_models';
import { DataService } from 'data.service';

@Component({
  selector: 'app-ver-carteleras',
  templateUrl: './ver-carteleras.component.html',
  styleUrls: ['./ver-carteleras.component.css']
})
export class VerCartelerasComponent implements OnInit {

  carteleras$: Object;
  currentUser: User;

  constructor(
    private data: DataService,
    private authenticationService: AuthenticationService
) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
}

  ngOnInit() {
    this.data.getCarteleras()
    .subscribe(
      data => this.carteleras$ =  data
    )
    
  }

}
