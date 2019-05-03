import { Component, OnInit , Inject , ViewChild} from '@angular/core';

import { AuthenticationService } from '_services';
import { User } from '_models';
import { ActivatedRoute } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA , MatTableDataSource, MatPaginator, MatSort} from '@angular/material';

//material table
import { CarteleraService } from '_services/cartelera.service'
import { PublicacionService } from '_services/publicacion.service'
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/collections'
import { Cartelera } from '_models/Cartelera'
import { Publicacion } from '_models/Publicacion'

export interface DialogData {
  publicacionBorrar: object;
}

@Component({
  selector: 'app-ver-cartelera',
  templateUrl: './ver-cartelera.component.html',
  styleUrls: ['./ver-cartelera.component.css']
})
export class VerCarteleraComponent implements OnInit {
  currentUser: User;
  publicacionElegida: Object;
  carteleraId$: string;

  //material table
  displayedColumns: string[] = ['titulo', 'owner', 'fecha', 'acciones'];
  dataSource: MatTableDataSource<Publicacion>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private authenticationService: AuthenticationService,
    public dialog: MatDialog,
    private carteleraService: CarteleraService,
    private publicacionService: PublicacionService,
    private route: ActivatedRoute
) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
}

applyFilter(filterValue: string) {
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}

openDialog(publicacion): void {
  this.publicacionElegida = publicacion;
  const dialogRef = this.dialog.open(DialogBorrarPublicacion, {
    width: '550px',
    data: {publicacionBorrar: this.publicacionElegida}
  });

  
  dialogRef.afterClosed().subscribe(result => {
    this.ngOnInit();
  });

}

configureDataSource(dataService){
  this.dataSource =  new MatTableDataSource(dataService);
  this.dataSource.paginator = this.paginator;
  this.dataSource.sort = this.sort;

  /*  No funciona
  this.dataSource.sortingDataAccessor = (item, property) => {
    switch(property) {
      case 'creador': 
        return `${item.creador.nombre} ${item.creador.apellido}`;
      default: return item[property];
    }
  };
  */

}

getPublicaciones(cartelera){
  let publicaciones:Publicacion[] = cartelera.publicaciones;
  this.configureDataSource(publicaciones);
}

//mat-paginator-range-label
  ngOnInit() {
    this.paginator._intl.itemsPerPageLabel = 'Publicaciones por pagina';
    this.paginator._intl.nextPageLabel = 'Pagina Siguiente';
    this.paginator._intl.previousPageLabel = 'Pagina Anterior';

    this.carteleraId$ = this.route.snapshot.paramMap.get('id');
    this.carteleraService.getCarteleraById(this.carteleraId$).subscribe(
      data =>{
        this.getPublicaciones(data);
      }
    )
  }


  toggleComentarios( element ){
    this.publicacionService.toggleComentarios(element).subscribe(
      data =>{
        this.ngOnInit();
      }
    );
  }
}

@Component({
  selector: 'dialog-borrar-publicacion',
  templateUrl: 'dialog-borrar-publicacion.html',
})
export class DialogBorrarPublicacion {
 
  constructor(

    private publicacionService: PublicacionService,
    public dialogRef: MatDialogRef<DialogBorrarPublicacion>,
    @Inject(MAT_DIALOG_DATA) public dataDialog: DialogData

    ) {}
    
  onNoClick(): void {
    this.dialogRef.close();
  }

  onClickDeletePublicacion(publicacion){
    this.publicacionService.deletePublicacion(publicacion)
    .subscribe(
      data => this.dialogRef.close()
    )
  }

}

export class PublicacionDataSource extends DataSource<any> {

  constructor(
    private carteleraCartelera: CarteleraService,
    private route: ActivatedRoute
    ){
    super();
  }

  connect(): Observable<Publicacion[]> {
   // this.carteleraService.getCarteleras().subscribe(data => console.log(data))
    let id = this.route.snapshot.paramMap.get('id');
    return this.carteleraCartelera.getPublicacionesCarteleraId(id);
  }

  disconnect(){}

} 