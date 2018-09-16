import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCardModule,
  MatDialogModule,
  MatInputModule, MatListModule,
  MatSidenavModule,
  MatTableModule,
  MatToolbarModule,
  MatIconModule, MatGridListModule,
} from '@angular/material';

@NgModule({
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatTableModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatGridListModule,
  ],
  exports: [
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatTableModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatGridListModule,
  ]
})
export class AngularMaterialModule {
}
