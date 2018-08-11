import {NgModule} from '@angular/core';
import {HomeComponent} from './home.component';
import {SharedModule} from '../shared/shared.module';
import {CoreModule} from '../core/core.module';

@NgModule({
  imports: [
    SharedModule,
    CoreModule
  ],
  declarations: [HomeComponent]
})
export class HomeModule {
}
