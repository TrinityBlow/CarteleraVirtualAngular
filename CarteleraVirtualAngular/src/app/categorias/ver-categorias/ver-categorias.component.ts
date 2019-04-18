import { Component, OnInit , Inject , ViewChild} from '@angular/core';

import { AuthenticationService } from '_services';
import { User } from '_models';
import { DataService } from 'data.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA , MatTableDataSource, MatPaginator, MatSort} from '@angular/material';

//material table
import { CategoriaService } from '_services/categoria.service'
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/collections'
import { Categoria } from '_models/Categoria'

export interface DialogData {
  categoriaBorrar: object;
}


@Component({
  selector: 'app-ver-categorias',
  templateUrl: './ver-categorias.component.html',
  styleUrls: ['./ver-categorias.component.css']
})
export class VerCategoriasComponent implements OnInit {
  currentUser: User;
  categoriaElegida: Object;

  //material table
  displayedColumns: string[] = ['tipo', 'acciones'];
  dataSource: MatTableDataSource<Categoria>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private authenticationService: AuthenticationService,
    public dialog: MatDialog,
    private categoriaService: CategoriaService
) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
}

applyFilter(filterValue: string) {
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}

openDialog(categoria): void {
  this.categoriaElegida = categoria;
  const dialogRef = this.dialog.open(DialogBorrarCategoria, {
    width: '550px',
    data: {categoriaBorrar: this.categoriaElegida}
  });

  
  dialogRef.afterClosed().subscribe(result => {
    this.ngOnInit();
  });

}

configureDataSource(dataService){
  this.dataSource =  new MatTableDataSource(dataService);
  this.dataSource.paginator = this.paginator;
  this.dataSource.sort = this.sort;
}

//mat-paginator-range-label
  ngOnInit() {
    this.paginator._intl.itemsPerPageLabel = 'Categorias por pagina';
    this.paginator._intl.nextPageLabel = 'Pagina Siguiente';
    this.paginator._intl.previousPageLabel = 'Pagina Anterior';

    this.categoriaService.getCategorias()
    .subscribe(
      dataService => this.configureDataSource(dataService)
    )
    
  }

}



@Component({
  selector: 'dialog-borrar-categoria',
  templateUrl: 'dialog-borrar-categoria.html',
})
export class DialogBorrarCategoria {
 
  constructor(

    private categoriaService: CategoriaService,
    public dialogRef: MatDialogRef<DialogBorrarCategoria>,
    @Inject(MAT_DIALOG_DATA) public dataDialog: DialogData

    ) {}
    
  onNoClick(): void {
    this.dialogRef.close();
  }

  onClickDeleteCategoria(categoria){
    this.categoriaService.deleteCategoria(categoria)
    .subscribe(
      data => this.dialogRef.close()
    )
  }

}

export class CategoriaDataSource extends DataSource<any> {

  constructor(private categoriaService: CategoriaService){
    super();
  }

  connect(): Observable<Categoria[]> {
   // this.carteleraService.getCarteleras().subscribe(data => console.log(data))
    return this.categoriaService.getCategorias();
  }

  disconnect(){}

} 