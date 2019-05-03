import { Component, OnInit, AfterViewInit} from '@angular/core';

import { AuthenticationService } from '_services';
import { User } from '_models';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { CarteleraService } from '_services/cartelera.service'
import { Cartelera } from '_models/Cartelera'
import { Categoria } from '_models/Categoria'
import { CategoriaService } from '_services/categoria.service';

@Component({
  selector: 'app-modificar-cartelera',
  templateUrl: './modificar-cartelera.component.html',
  styleUrls: ['./modificar-cartelera.component.css']
})
export class ModificarCarteleraComponent implements OnInit {

  currentUser: User;
  cartelera$: Cartelera;
  categorias$: Categoria[];
  modificarCarteleraForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  success = '';

  constructor(
    private authenticationService: AuthenticationService,
    private carteleraService: CarteleraService,
    private categoriaService: CategoriaService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
    ) { 
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }

    modificarForm(){
      
        this.modificarCarteleraForm = this.formBuilder.group({
          id: ['', Validators.required],
          titulo: ['', Validators.required],
          categoria: ['', Validators.required]
      });
    }

    modificarFormAfterGettingValue(){
      this.modificarCarteleraForm = this.formBuilder.group({
        id: [this.cartelera$.id, Validators.required],
        titulo: [this.cartelera$.titulo, Validators.required],
        categoria: [this.cartelera$.categoria.tipo, Validators.required]
    });
  }

  buscarCategorias(){   
    this.categoriaService.getCategorias()
    .subscribe(
      data => {
        this.categorias$ = data;
        for (var i = this.categorias$.length - 1; i >= 0; --i) {
          if (this.categorias$[i].tipo == this.cartelera$.categoria.tipo) {
            this.categorias$.splice(i,1);
          }
       }
      }
    );  
  }
  
  buscarCartelera(){
    let id = this.route.snapshot.paramMap.get('id');
    this.carteleraService.getById(id).subscribe(
      data => {
        this.cartelera$ = data;
        this.modificarFormAfterGettingValue();
        this.buscarCategorias();
      }
    );
  }

  ngOnInit() {
    this.cartelera$ = this.carteleraService.newEmptyCartelera();
    this.categorias$ = [];

    this.modificarForm();

    this.buscarCartelera();
  }

  
  get f() { return this.modificarCarteleraForm.controls; }

  onSubmit() {
    this.submitted = true;
    // Valido que el formulario sea valido antes del submit
    if (this.modificarCarteleraForm.invalid) {
        return;
    }

    this.loading = true;
    this.carteleraService.modificarCartelera(this.f)
    .pipe(first())
    .subscribe(
        data => {
          this.success = "Cartelera modificada con exito"
          this.loading = false;
          this.buscarCartelera();
        },
        error => {
            this.error = 'Cartelera ya existente o error en el servidor';
            this.loading = false;
        });
    }

}
