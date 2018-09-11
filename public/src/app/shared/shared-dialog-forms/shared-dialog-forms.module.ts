import {NgModule} from '@angular/core';
import {ConfirmDeleteFormComponent} from './confirm-delete-form/confirm-delete-form.component';
import {EditUserDialogFormComponent} from './edit-user-dialog-form/edit-user-dialog-form.component';
import {SharedModule} from 'src/app/shared/shared.module';
import {MessageService} from 'primeng/api';
import {EditComponentDialogFormComponent} from './edit-component-dialog-form/edit-component-dialog-form.component';

const components = [
  ConfirmDeleteFormComponent,
  EditUserDialogFormComponent,
  EditComponentDialogFormComponent,
];

@NgModule({
  imports: [
    SharedModule
  ],
  declarations: components,
  exports: components,
  entryComponents: components,
  providers: [
    MessageService,
  ]

})
export class SharedDialogFormsModule {
}
