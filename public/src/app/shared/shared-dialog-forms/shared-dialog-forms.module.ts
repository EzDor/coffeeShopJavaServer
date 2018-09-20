import {NgModule} from '@angular/core';
import {ConfirmDeleteAdminTableFormComponent} from './confirm-delete-form/confirm-delete-admin-table-form.component';
import {EditUserDialogFormComponent} from './edit-user-dialog-form/edit-user-dialog-form.component';
import {SharedModule} from 'src/app/shared/shared.module';
import {EditComponentDialogFormComponent} from './edit-component-dialog-form/edit-component-dialog-form.component';
import {EditProductDialogFormComponent} from './edit-product-dialog-form/edit-product-dialog-form.component';
import {NewOrderDialogFormComponent} from './new-order-dialog-form/new-order-dialog-form.component';
import {ConfirmDeleteCartTableFormComponent} from '@dialogs/confirm-delete-form/confirm-delete-cart-table-form.component';
import {CheckoutDialogFormComponent} from '@dialogs/checkout-dialog-form/checkout-dialog-form.component';

const components = [
  ConfirmDeleteAdminTableFormComponent,
  ConfirmDeleteCartTableFormComponent,
  EditUserDialogFormComponent,
  EditComponentDialogFormComponent,
  EditProductDialogFormComponent,
  NewOrderDialogFormComponent,
  CheckoutDialogFormComponent,
];

@NgModule({
  imports: [
    SharedModule
  ],
  declarations: components,
  exports: components,
  entryComponents: components,
  providers: [ ]

})
export class SharedDialogFormsModule {
}
