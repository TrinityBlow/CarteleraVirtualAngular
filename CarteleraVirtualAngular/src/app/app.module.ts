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
import { CreateCategoriaComponent } from './categorias/create-categoria/create-categoria.component';
import { CarteleraService } from '_services/cartelera.service';
import { UserService } from '_services/user.service';
import { PublicacionService } from '_services/publicacion.service';
import { ComentarioService } from '_services/comentario.service';
import { DialogBorrarPublicacion } from 'carteleras/ver-cartelera/ver-cartelera.component';
import { MatTableModule } from '@angular/material';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import { VerCategoriasComponent, DialogBorrarCategoria  } from './categorias/ver-categorias/ver-categorias.component'
import {MatCardModule} from '@angular/material/card';
import { ModificarCarteleraComponent } from './carteleras/modificar-cartelera/modificar-cartelera.component';
import { VerCarteleraComponent } from './carteleras/ver-cartelera/ver-cartelera.component';
import { VerPublicacionComponent } from './publicaciones/ver-publicacion/ver-publicacion.component';
import { CreatePublicacionComponent } from './publicaciones/create-publicacion/create-publicacion.component';
import { AgregarOwnersComponent } from './carteleras/agregar-owners/agregar-owners.component';
import { MatFormFieldModule, MatSelectModule } from '@angular/material';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    CreateUserComponent,
    CreateCarteleraComponent,
    VerCartelerasComponent,
    DialogBorrarCartelera,
    DialogBorrarCategoria,
    DialogBorrarPublicacion,
    CreateCategoriaComponent,
    VerCategoriasComponent,
    ModificarCarteleraComponent,
    VerCarteleraComponent,
    VerPublicacionComponent,
    CreatePublicacionComponent,
    AgregarOwnersComponent
    
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
    MatCardModule,
    MatSelectModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    NgxMatSelectSearchModule
  ],
  entryComponents: [
    DialogBorrarCartelera,
    DialogBorrarCategoria,
    DialogBorrarPublicacion
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    CarteleraService,
    UserService,
    PublicacionService,
    ComentarioService
],
  bootstrap: [AppComponent]
})
export class AppModule { }
