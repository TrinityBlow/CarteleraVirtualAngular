import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../_services';
import { DataService } from '../data.service';
import { User } from '../_models';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  createForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  currentUser: User;
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
      this.createForm = this.formBuilder.group({
          nombre: ['', Validators.required],
          apellido: ['', Validators.required],
          emailC: ['', Validators.required],
          rol: ['', Validators.required],
          passwordC: ['', Validators.required]
      });

      this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/create-user'; 

    }

    get f() { return this.createForm.controls; }

    onSubmit() {
      this.submitted = true;
      this.success = '';
      this.error = '';

      // Valido que el formulario sea valido antes del submit
      if (this.createForm.invalid) {
          return;
      }

      this.loading = true;
      this.data.createUser(this.f)
      .pipe(first())
      .subscribe(
          data => {
            this.success = "Usuario creado con exito"
            this.loading = false;
          },
          error => {
              this.error = 'Email ya existente o error en el servidor';
              this.loading = false;
          });
  }

  }
