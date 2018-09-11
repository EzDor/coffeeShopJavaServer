import {NgModule} from '@angular/core';
import {
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

@NgModule({
  imports: [
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
  ],
  exports: [
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
  ]
})
export class NgPrimeModule {
}
