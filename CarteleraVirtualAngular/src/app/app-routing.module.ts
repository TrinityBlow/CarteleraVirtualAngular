import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DialogOverviewExampleComponent } from './test/dialog-overview-example/dialog-overview-example.component';
import { MaterialTableComponent } from './test/material-table/material-table.component';
import { AuthGuard, AuthAdminGuard } from './_guards';
import { CreateUserComponent } from './Login/create-user.component';
import { CreateCarteleraComponent } from './carteleras/create-cartelera/create-cartelera.component';
import { VerCartelerasComponent } from './carteleras/ver-carteleras/ver-carteleras.component';
import { CreateCategoriaComponent } from './categorias/create-categoria/create-categoria.component'; 
import { MaterialTableGitComponent } from 'test/material-table-git/material-table-git.component';
import { VerCategoriasComponent } from './categorias/ver-categorias/ver-categorias.component'

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent },
  { path: 'carteleras/create-cartelera', component: CreateCarteleraComponent, canActivate: [AuthAdminGuard] },
  { path: 'carteleras/ver-carteleras', component: VerCartelerasComponent, canActivate: [AuthGuard] },
  { path: 'test', component: DialogOverviewExampleComponent },
  { path: 'testTable', component: MaterialTableComponent },
  { path: 'testTableGit', component: MaterialTableGitComponent },
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
