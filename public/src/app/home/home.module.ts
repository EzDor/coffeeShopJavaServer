import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import {SharedModule} from '@shared/shared.module';
import {ProductModule} from '@app/home/products/product.module';
import {HomeComponent} from '@app/home/home.component';
import { CartComponent } from './cart/cart.component';
import {CartTableComponent} from '@app/home/cart/cart-table.component';

@NgModule({
  imports: [
    SharedModule,
    ProductModule,
    HomeRoutingModule
  ],
  declarations: [
    HomeComponent,
    CartComponent,
    CartTableComponent
  ],
  entryComponents: [
  ],
  exports: [
  ]
})
export class HomeModule { }
