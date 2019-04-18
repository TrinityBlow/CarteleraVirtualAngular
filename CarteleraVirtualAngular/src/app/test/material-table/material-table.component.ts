import { Component, OnInit } from '@angular/core';
import { CarteleraService } from '_services/cartelera.service'
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/collections'
import { Cartelera } from '_models/Cartelera'
import { MatTableDataSource } from '@angular/material'

@Component({
  selector: 'app-material-table',
  templateUrl: './material-table.component.html',
  styleUrls: ['./material-table.component.css']
})
export class MaterialTableComponent implements OnInit {
  displayedColumns: string[] = ['titulo'];
 // dataSource = new CarteleraDataSource(this.carteleraService);
  dataSource: MatTableDataSource<Cartelera>;

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor(private carteleraService: CarteleraService) { }

  ngOnInit() {
    this.carteleraService.getCarteleras()
    .subscribe(
      data => this.dataSource = new MatTableDataSource(data)
    )
  }

}

export class CarteleraDataSource extends DataSource<any> {

  constructor(private carteleraService: CarteleraService){
    super();
  }

  connect(): Observable<Cartelera[]> {
   // this.carteleraService.getCarteleras().subscribe(data => console.log(data))
    return this.carteleraService.getCarteleras();
  }

  disconnect(){}

} 
