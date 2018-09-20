import {ErrorHandler, NgModule} from '@angular/core';
import {NavBarComponent} from './nav-bar.component';
import {SharedModule} from '@shared/shared.module';
import {SharedDialogFormsModule} from '@dialogs/shared-dialog-forms.module';
import {AppErrorHandler} from './app-error-handler';
import {ConfirmDeleteAdminTableFormComponent} from '@dialogs/confirm-delete-form/confirm-delete-admin-table-form.component';
import { NotFoundComponent } from './not-found/not-found.component';

@NgModule({
  imports: [
    SharedModule,
    SharedDialogFormsModule,
  ],
  exports: [
    NavBarComponent
  ],
  declarations: [
    NavBarComponent,
    NotFoundComponent
  ],
  entryComponents: [
    ConfirmDeleteAdminTableFormComponent
  ],
  providers: [
    {
      provide: ErrorHandler,
      useClass: AppErrorHandler
    }
  ]
})
export class ShellModule {
}
