import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '_services';
import { DataService } from 'data.service';
import { User } from '_models';

@Component({
  selector: 'app-create-categoria',
  templateUrl: './create-categoria.component.html',
  styleUrls: ['./create-categoria.component.css']
})
export class CreateCategoriaComponent implements OnInit {

  currentUser: User;
  createCategoriaForm: FormGroup;
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
    this.createCategoriaForm = this.formBuilder.group({
        tipoC: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/categorias/create-categoria'; 

  } 

  get f() { return this.createCategoriaForm.controls; }


  onSubmit() {
    this.submitted = true;
    // Valido que el formulario sea valido antes del submit
    if (this.createCategoriaForm.invalid) {
        return;
    }

    this.loading = true;
    this.data.createCategoria(this.f)
    .pipe(first())
    .subscribe(
        data => {
          this.success = "Categoria creado con exito"
          this.loading = false;
        },
        error => {
            this.error = 'Categoria ya existente o error en el servidor';
            this.loading = false;
        });
    }


}
