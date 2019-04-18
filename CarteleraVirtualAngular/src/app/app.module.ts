import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { routing } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CreateUserComponent } from './Login/create-user.component';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';

import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { CreateCarteleraComponent } from './carteleras/create-cartelera/create-cartelera.component';
import { VerCartelerasComponent, DialogBorrarCartelera } from './carteleras/ver-carteleras/ver-carteleras.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material';
import { DialogOverviewExampleComponent, DialogOverviewExampleDialog } from './test/dialog-overview-example/dialog-overview-example.component';
import { CreateCategoriaComponent } from './categorias/create-categoria/create-categoria.component';
import { CarteleraService } from '_services/cartelera.service';
import { MaterialTableComponent } from './test/material-table/material-table.component';
import { MatTableModule } from '@angular/material';
import { MaterialTableGitComponent } from './test/material-table-git/material-table-git.component';
import { UserService } from '_services/user.service';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import { VerCategoriasComponent, DialogBorrarCategoria  } from './categorias/ver-categorias/ver-categorias.component'
import {MatCardModule} from '@angular/material/card';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    CreateUserComponent,
    CreateCarteleraComponent,
    VerCartelerasComponent,
    DialogBorrarCartelera,
    DialogOverviewExampleComponent,
    DialogOverviewExampleDialog,
    DialogBorrarCategoria,
    CreateCategoriaComponent,
    MaterialTableComponent,
    MaterialTableGitComponent,
    VerCategoriasComponent
    
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatCardModule
  ],
  entryComponents: [
    DialogOverviewExampleDialog,
    DialogBorrarCartelera,
    DialogBorrarCategoria
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    CarteleraService,
    UserService
],
  bootstrap: [AppComponent]
})
export class AppModule { }
