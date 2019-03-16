import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RxjsTestComponent } from './rxjs-test/rxjs-test.component';
import { AuthGuard, AuthAdminGuard } from './_guards';
import { CreateUserComponent } from './Login/create-user.component';
import { CreateCarteleraComponent } from './carteleras/create-cartelera/create-cartelera.component';
import { VerCartelerasComponent } from './carteleras/ver-carteleras/ver-carteleras.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent },
  { path: 'carteleras/create-cartelera', component: CreateCarteleraComponent, canActivate: [AuthAdminGuard] },
  { path: 'carteleras/ver-carteleras', component: VerCartelerasComponent, canActivate: [AuthGuard] },
  { path: 'test', component: RxjsTestComponent },
  { path: 'create-user', component: CreateUserComponent, canActivate: [AuthAdminGuard] },

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
