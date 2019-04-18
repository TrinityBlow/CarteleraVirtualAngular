
import { Component, OnInit } from '@angular/core';
import { UserService } from '_services/user.service';

import { Observable } from 'rxjs';
import {DataSource} from '@angular/cdk/collections';
import { UserGit } from '_models/userGit';

@Component({
  selector: 'usertable',
  templateUrl: './material-table-git.component.html'
})
export class MaterialTableGitComponent implements OnInit {
  dataSource = new UserDataSource(this.userService);
  displayedColumns = ['name', 'email', 'phone', 'company'];

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

}

export class UserDataSource extends DataSource<any> {

  constructor(private userService: UserService) {
    super();
  }

  connect(): Observable<UserGit[]> {
    return this.userService.getUser();
  }

  disconnect() {}
}