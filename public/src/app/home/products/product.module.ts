import {NgModule} from '@angular/core';
import {ProductMenuGridComponent} from './product-menu-grid.component';
import {ProductMenuCardComponent} from './product-menu-card.component';
import {SharedModule} from '../../shared/shared.module';
import {ProductService} from '../../core/services/product.service';

@NgModule({
  imports: [
    SharedModule,
  ],
  declarations: [
    ProductMenuCardComponent,
    ProductMenuGridComponent
  ],
  exports: [ProductMenuGridComponent],
})
export class ProductModule {
}
