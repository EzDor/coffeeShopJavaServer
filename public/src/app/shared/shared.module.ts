import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoadingSpinnerComponent} from './loading-spinner.componnent';
import {CoffeeShopLogoComponent} from './coffee-shop-logo/coffee-shop-logo.component';
import {AngularMaterialModule} from './angular-material/angular-material.module';
import {NgPrimeModule} from './ng-prime/ng-prime.module';
import {RouterModule} from '@angular/router';
import {ObjectKeysPipe} from './pipes/object-keys.pipe';
import {NgBootstrapModule} from './ng-bootstrap/ng-bootstrap.module';
import {EnumToArrayPipe} from './pipes/enum-to-array.pipe';


@NgModule({
  imports: [
    CommonModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgPrimeModule,
    RouterModule,
    NgBootstrapModule,
  ],
  exports: [
    CoffeeShopLogoComponent,
    LoadingSpinnerComponent,
    ObjectKeysPipe,
    EnumToArrayPipe,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AngularMaterialModule,
    NgPrimeModule,
    RouterModule,
    NgBootstrapModule,
  ],
  declarations: [
    LoadingSpinnerComponent,
    CoffeeShopLogoComponent,
    ObjectKeysPipe,
    EnumToArrayPipe,
  ],
})

export class SharedModule {
}
