import {NgModule} from '@angular/core';
import {ProductMenuGridComponent} from './product-menu-grid.component';
import {ProductMenuCardComponent} from './product-menu-card.component';
import {SharedModule} from '../../shared/shared.module';
import {CoreModule} from '../../core/core.module';
import {ProductService} from './product.service';

@NgModule({
  imports: [
    SharedModule,
    CoreModule,
  ],
  declarations: [
    ProductMenuCardComponent,
    ProductMenuGridComponent
  ],
  providers: [
    ProductService
  ],
  exports: [ProductMenuGridComponent],
})
export class ProductModule {
}
