import {NgModule} from '@angular/core';
import {
  AutoCompleteModule,
  ButtonModule,
  DialogModule,
  DropdownModule,
  InputTextModule,
  PanelModule,
  TabMenuModule,
  TabViewModule
} from 'primeng/primeng';
import {DataViewModule} from 'primeng/dataview';
import {ToastModule} from 'primeng/toast';

const modules = [
  TabViewModule,
  TabMenuModule,
  DataViewModule,
  PanelModule,
  DialogModule,
  DropdownModule,
  TabViewModule,
  InputTextModule,
  ButtonModule,
  ToastModule,
  AutoCompleteModule,
];

@NgModule({
  imports: modules,
  exports: modules
})
export class NgPrimeModule {
}
