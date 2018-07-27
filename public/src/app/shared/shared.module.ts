import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LoadingSpinnerComponent} from './loading-spinner.componnent';

@NgModule({
  imports: [CommonModule],
  exports: [LoadingSpinnerComponent, CommonModule],
  declarations: [LoadingSpinnerComponent],
  providers: []
})

export class SharedModule {
}
