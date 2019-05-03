import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ReplaySubject, Subject } from 'rxjs';
import { take, takeUntil } from 'rxjs/operators';
import { MatSelect } from '@angular/material';
import { SimpleUser } from '_models/SimpleUser';
import { UserService } from '_services/user.service';
import { CarteleraService, AuthenticationService } from '_services';
import { ActivatedRoute } from '@angular/router';
import { User } from '_models';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-agregar-owners',
  templateUrl: './agregar-owners.component.html',
  styleUrls: ['./agregar-owners.component.css']
})
export class AgregarOwnersComponent implements OnInit, AfterViewInit, OnDestroy {

  /** list of banks */
  protected usuariosSeleccion: SimpleUser[];

  /** control for the selected bank for multi-selection */
  public usuariosMultiCtrl: FormControl = new FormControl();

  /** control for the MatSelect filter keyword multi-selection */
  public usuariosMultiFilterCtrl: FormControl = new FormControl();

  /** list of banks filtered by search keyword */
  public filteredUsuariosMulti: ReplaySubject<SimpleUser[]> = new ReplaySubject<SimpleUser[]>(1);

  @ViewChild('multiSelect') multiSelect: MatSelect;

  /** Subject that emits when the component has been destroyed. */
  protected _onDestroy = new Subject<void>();

  currentUser: User;
  ownersCartelera: SimpleUser[];
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  success = '';


  constructor(
    private authenticationService: AuthenticationService,
    private usuarioService: UserService,
    private carteleraService: CarteleraService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { 
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  dataLoaded(){
    // set initial selection
    this.usuariosMultiCtrl.setValue(this.ownersCartelera);

    // load the initial bank list
    this.filteredUsuariosMulti.next(this.usuariosSeleccion.slice());

    // listen for search field value changes
    this.usuariosMultiFilterCtrl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.filterBanksMulti();
      });
  }

  nextLoad(){  
    this.usuarioService.getUsersOwners().subscribe(
      dataUsers => {
        this.usuariosSeleccion = dataUsers;
        this.dataLoaded();
      }
    );
  }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    this.carteleraService.getOwnersCartelera(id).subscribe(
      dataOwners=>{
        this.ownersCartelera = dataOwners;
        this.nextLoad();
      }
    );
  }

  ngAfterViewInit() {
    this.setInitialValue();
  }

  ngOnDestroy() {
    this._onDestroy.next();
    this._onDestroy.complete();
  }

  /**
   * Sets the initial value after the filteredBanks are loaded initially
   */
  protected setInitialValue() {
    this.filteredUsuariosMulti
      .pipe(take(1), takeUntil(this._onDestroy))
      .subscribe(() => {
        // setting the compareWith property to a comparison function
        // triggers initializing the selection according to the initial value of
        // the form control (i.e. _initializeSelection())
        // this needs to be done after the filteredBanks are loaded initially
        // and after the mat-option elements are available
        this.multiSelect.compareWith = (a: SimpleUser, b: SimpleUser) => a && b && a.id === b.id;
      });
  }

  protected filterBanksMulti() {
    if (!this.usuariosSeleccion) {
      return;
    }
    // get the search keyword
    let search = this.usuariosMultiFilterCtrl.value;
    if (!search) {
      this.filteredUsuariosMulti.next(this.usuariosSeleccion.slice());
      return;
    } else {
      search = search.toLowerCase();
    }
    // filter the banks
    this.filteredUsuariosMulti.next(
      this.usuariosSeleccion.filter(usuario => usuario.email.toLowerCase().indexOf(search) > -1)
    );
  }


  onSubmit() {
    
    this.submitted = true;
    // Valido que el formulario sea valido antes del submit
    if (this.usuariosMultiCtrl.invalid) {
        return;
    }
    console.log(this.usuariosMultiCtrl.value);
    this.loading = true;


    let carteleraId = this.route.snapshot.paramMap.get('id');    
    let nuevosOwners = this.usuariosMultiCtrl.value
    let nuevosOwnersEmail:String[] = [];

    for (var i = nuevosOwners.length - 1; i >= 0; --i) {
      nuevosOwnersEmail.push(nuevosOwners[i].email);
    }


    this.carteleraService.nuevosOwnersCartelera(carteleraId,nuevosOwnersEmail)
    .pipe(first())
    .subscribe(
        data => {
          this.success = "Cartelera modificada con exito"
          this.loading = false;
          console.log(data);
        },
        error => {
            this.error = 'Cartelera ya existente o error en el servidor';
            this.loading = false;
        });


  }


}
