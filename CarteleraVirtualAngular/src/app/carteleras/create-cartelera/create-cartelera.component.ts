import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '_services';
import { DataService } from 'data.service';
import { User } from '_models';

@Component({
  selector: 'app-create-cartelera',
  templateUrl: './create-cartelera.component.html',
  styleUrls: ['./create-cartelera.component.css']
})
export class CreateCarteleraComponent implements OnInit {

  categorias$: Object;
  categoriasSize$: number;
  currentUser: User;
  createCarteleraForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  success = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private data: DataService
) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
}

  ngOnInit() {
    this.categorias$ = null;
    this.createCarteleraForm = this.formBuilder.group({
        titulo: ['', Validators.required],
        categoria: ['', Validators.required]
    });

    this.data.getCategorias()
    .subscribe(
      data => this.categorias$ =  data
    )

    this.data.getCategorias()
    .subscribe(
      data => this.categoriasSize$ = Object.values(data).length
    )

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/create-cartelera'; 

  }
  
  get f() { return this.createCarteleraForm.controls; }

  
  onSubmit() {
    this.submitted = true;
    // Valido que el formulario sea valido antes del submit
    if (this.createCarteleraForm.invalid) {
        return;
    }

    this.loading = true;
    this.data.createCartelera(this.f,this.currentUser.username)
    .pipe(first())
    .subscribe(
        data => {
          this.success = "Cartelera creado con exito"
          this.loading = false;
        },
        error => {
            this.error = 'Cartelera ya existente o error en el servidor';
            this.loading = false;
        });
    }

}
