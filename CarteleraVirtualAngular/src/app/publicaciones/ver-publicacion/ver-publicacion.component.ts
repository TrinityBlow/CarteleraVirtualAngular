import { Component, OnInit , Inject , ViewChild} from '@angular/core';


import { AuthenticationService } from '_services';
import { User } from '_models';
import { ActivatedRoute } from '@angular/router';

import { PublicacionService } from '_services/publicacion.service'
import { ComentarioService } from '_services/comentario.service'
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/collections'
import { Publicacion } from '_models/Publicacion'
import { Comentario } from '_models/Comentario';

@Component({
  selector: 'app-ver-publicacion',
  templateUrl: './ver-publicacion.component.html',
  styleUrls: ['./ver-publicacion.component.css']
})
export class VerPublicacionComponent implements OnInit {

  currentUser: User;
  publicacion$: Publicacion;
  comentarios$: Object;


  constructor(
    private authenticationService: AuthenticationService,
    private publicacionService: PublicacionService,
    private comentarioService: ComentarioService,
    private route: ActivatedRoute
    ) { 
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }

  ngOnInit() {
   

    let id = this.route.snapshot.paramMap.get('id');
    this.publicacionService.getPublicacion(id).subscribe(
      dataPublicacion =>{
        this.publicacion$ = dataPublicacion;
      }
    );
    this.comentarioService.getComentarioDePublicacion(id).subscribe(
      dataComentarios =>{
        this.comentarios$ = dataComentarios;
      }
    );
    
    
  }

  
  addHero(newHero: string, comentario:Comentario) {
    if (newHero) {
      this.comentarioService.responderComentario(comentario.id,newHero,this.currentUser.username).subscribe(
        data =>{
          this.ngOnInit();
        }
      );
    }
  }

  addHeroComentario(newHeroComentario:string){
    if (newHeroComentario) {
      this.comentarioService.crearComentario(newHeroComentario,this.currentUser.username,this.publicacion$.id).subscribe(
        data =>{
          this.ngOnInit();
        }
      );
    }

  }

}
