import {NgModule} from '@angular/core';
import {HomeComponent} from './home.component';
import {SharedModule} from '../shared/shared.module';
import {RouterModule} from '@angular/router';

@NgModule({
  imports: [
    SharedModule,
    RouterModule
  ],
  declarations: [HomeComponent]
})
export class HomeModule {
}
