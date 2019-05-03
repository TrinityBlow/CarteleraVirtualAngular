import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '_services';
import { PublicacionService } from '_services/publicacion.service';
import { User } from '_models';


@Component({
  selector: 'app-create-publicacion',
  templateUrl: './create-publicacion.component.html',
  styleUrls: ['./create-publicacion.component.css']
})
export class CreatePublicacionComponent implements OnInit {

  currentUser: User;
  createPublicacionForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  success = '';
  carteleraId$: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private publicacionServices: PublicacionService
) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
}

  ngOnInit() {
    this.carteleraId$ = this.route.snapshot.paramMap.get('id');

    this.createPublicacionForm = this.formBuilder.group({
      tituloP: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/categorias/create-categoria'; 

  } 

  get f() { return this.createPublicacionForm.controls; }


  onSubmit() {
    this.submitted = true;
    // Valido que el formulario sea valido antes del submit
    if (this.createPublicacionForm.invalid) {
        return;
    }

    this.loading = true;
    this.publicacionServices.createPublicacion(this.f,this.carteleraId$, this.currentUser.username)
    .pipe(first())
    .subscribe(
        data => {
          this.success = "Publicacion creado con exito"
          this.loading = false;
        },
        error => {
            this.error = 'Error en el servidor';
            this.loading = false;
        });
    }


}
