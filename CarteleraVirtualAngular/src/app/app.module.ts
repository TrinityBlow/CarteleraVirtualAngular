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
import { RxjsTestComponent } from './rxjs-test/rxjs-test.component';
import { CreateCarteleraComponent } from './carteleras/create-cartelera/create-cartelera.component';
import { VerCartelerasComponent } from './carteleras/ver-carteleras/ver-carteleras.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    CreateUserComponent,
    RxjsTestComponent,
    CreateCarteleraComponent,
    VerCartelerasComponent
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
