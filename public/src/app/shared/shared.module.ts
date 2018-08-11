import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoadingSpinnerComponent} from './loading-spinner.componnent';
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
import {CoffeeShopLogoComponent} from './coffee-shop-logo/coffee-shop-logo.component';


@NgModule({
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatTableModule,
    FormsModule,
    ReactiveFormsModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatGridListModule,
  ],
  exports: [
    CoffeeShopLogoComponent,
    LoadingSpinnerComponent,
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatTableModule,
    FormsModule,
    ReactiveFormsModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatGridListModule,
  ],
  declarations: [
    LoadingSpinnerComponent,
    CoffeeShopLogoComponent,
  ],
})

export class SharedModule {
}
