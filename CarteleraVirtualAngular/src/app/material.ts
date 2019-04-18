import {MatDialogModule} from '@angular/material/dialog';
import {NgModule} from '@angular/core'
import { FormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material';


@NgModule({
    imports: [
      MatDialogModule,
      FormsModule,
      MatFormFieldModule,
      MatInputModule
    ],
    exports: [
      MatDialogModule,
      FormsModule,
      MatFormFieldModule,
      MatInputModule
    ],
  })

  export class MaterialModule { }