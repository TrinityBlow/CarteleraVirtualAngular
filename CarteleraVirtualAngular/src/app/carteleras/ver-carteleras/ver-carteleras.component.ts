import { Component, OnInit , Inject , ViewChild} from '@angular/core';

import { AuthenticationService } from '_services';
import { User } from '_models';
import { DataService } from 'data.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA , MatTableDataSource, MatPaginator, MatSort} from '@angular/material';

//material table
import { CarteleraService } from '_services/cartelera.service'
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/collections'
import { Cartelera } from '_models/Cartelera'

export interface DialogData {
  carteleraBorrar: object;
}


@Component({
  selector: 'app-ver-carteleras',
  templateUrl: './ver-carteleras.component.html',
  styleUrls: ['./ver-carteleras.component.css']
})

export class VerCartelerasComponent implements OnInit {
  currentUser: User;
  carteleraElegida: Object;

  //material table
  displayedColumns: string[] = ['titulo', 'categoria', 'owners', 'fecha', 'acciones'];
  dataSource: MatTableDataSource<Cartelera>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private authenticationService: AuthenticationService,
    public dialog: MatDialog,
    private carteleraService: CarteleraService
) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
}

applyFilter(filterValue: string) {
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}

openDialog(cartelera): void {
  this.carteleraElegida = cartelera;
  const dialogRef = this.dialog.open(DialogBorrarCartelera, {
    width: '550px',
    data: {carteleraBorrar: this.carteleraElegida}
  });

  
  dialogRef.afterClosed().subscribe(result => {
    this.ngOnInit();
  });

}

configureDataSource(dataService){
  this.dataSource =  new MatTableDataSource(dataService);
  this.dataSource.paginator = this.paginator;
  this.dataSource.sort = this.sort;
  this.dataSource.sortingDataAccessor = (item, property) => {
    switch(property) {
      case 'categoria': return item.categoria.tipo;
      default: return item[property];
    }
  };
}

//mat-paginator-range-label
  ngOnInit() {
    this.paginator._intl.itemsPerPageLabel = 'Carteleras por pagina';
    this.paginator._intl.nextPageLabel = 'Pagina Siguiente';
    this.paginator._intl.previousPageLabel = 'Pagina Anterior';

    this.carteleraService.getCarteleras()
    .subscribe(
      dataService => {
        this.configureDataSource(dataService);
      }
    )
  }
}

@Component({
  selector: 'dialog-borrar-cartelera',
  templateUrl: 'dialog-borrar-cartelera.html',
})
export class DialogBorrarCartelera {
 
  constructor(

    private data: DataService,
    public dialogRef: MatDialogRef<DialogBorrarCartelera>,
    @Inject(MAT_DIALOG_DATA) public dataDialog: DialogData

    ) {}
    
  onNoClick(): void {
    this.dialogRef.close();
  }

  onClickDeleteCartelera(cartelera){
    this.data.deleteCartelera(cartelera)
    .subscribe(
      data => this.dialogRef.close()
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