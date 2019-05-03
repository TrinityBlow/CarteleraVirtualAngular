import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard, AuthAdminGuard } from './_guards';
import { CreateUserComponent } from './Login/create-user.component';
import { CreateCarteleraComponent } from './carteleras/create-cartelera/create-cartelera.component';
import { VerCartelerasComponent } from './carteleras/ver-carteleras/ver-carteleras.component';
import { ModificarCarteleraComponent } from './carteleras/modificar-cartelera/modificar-cartelera.component';
import { VerCarteleraComponent } from './carteleras/ver-cartelera/ver-cartelera.component';
import { AgregarOwnersComponent } from './carteleras/agregar-owners/agregar-owners.component';
import { CreateCategoriaComponent } from './categorias/create-categoria/create-categoria.component'; 
import { VerCategoriasComponent } from './categorias/ver-categorias/ver-categorias.component'
import { VerPublicacionComponent } from 'publicaciones/ver-publicacion/ver-publicacion.component';
import { CreatePublicacionComponent } from 'publicaciones/create-publicacion/create-publicacion.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent },
  { path: 'carteleras/create-cartelera', component: CreateCarteleraComponent, canActivate: [AuthAdminGuard] },
  { path: 'carteleras/modificar-cartelera/:id', component: ModificarCarteleraComponent, canActivate: [AuthAdminGuard] },
  { path: 'carteleras/agregar-owners/:id', component: AgregarOwnersComponent, canActivate: [AuthAdminGuard] },
  { path: 'carteleras/ver-carteleras', component: VerCartelerasComponent, canActivate: [AuthGuard] },
  { path: 'carteleras/ver-cartelera/:id', component: VerCarteleraComponent, canActivate: [AuthGuard] },
  { path: 'publicaciones/ver-publicacion/:id', component: VerPublicacionComponent, canActivate: [AuthGuard] },
  { path: 'publicaciones/create-publicacion/:id', component: CreatePublicacionComponent, canActivate: [AuthGuard] },
  { path: 'create-user', component: CreateUserComponent, canActivate: [AuthAdminGuard] },
  { path: 'categorias/create-categoria', component: CreateCategoriaComponent, canActivate: [AuthAdminGuard] },
  { path: 'categorias/ver-categorias', component: VerCategoriasComponent, canActivate: [AuthAdminGuard] },

  // otherwise redirect to home
  { path: '**', redirectTo: '/home' }
];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
