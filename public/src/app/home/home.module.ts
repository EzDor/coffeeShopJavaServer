import {NgModule} from '@angular/core';
import {HomeComponent} from './home.component';
import {SharedModule} from '../shared/shared.module';
import {ProductModule} from './products/product.module';
import {RouterModule} from '@angular/router';
import {Constants} from '../models/constants';


@NgModule({
  imports: [
    SharedModule,
    ProductModule,
    RouterModule.forChild([
      {path: Constants.HOME_PATH, component: HomeComponent},
    ])
  ],
  declarations: [
    HomeComponent,
  ],
  entryComponents: [
  ],
  exports: [
  ]
})
export class HomeModule {
}
