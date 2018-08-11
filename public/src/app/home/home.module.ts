import {NgModule} from '@angular/core';
import {HomeComponent} from './home.component';
import {SharedModule} from '../shared/shared.module';
import {CoreModule} from '../core/core.module';
import {ProductModule} from './products/product.module';

@NgModule({
  imports: [
    SharedModule,
    CoreModule,
    ProductModule,
  ],
  declarations: [
    HomeComponent,
  ]
})
export class HomeModule {
}
