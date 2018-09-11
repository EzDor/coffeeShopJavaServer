import {ErrorHandler, NgModule} from '@angular/core';
import {NavBarComponent} from './nav-bar.component';
import {SharedModule} from '../shared/shared.module';
import {SharedDialogFormsModule} from '../shared/shared-dialog-forms/shared-dialog-forms.module';
import {ConfirmDeleteFormComponent} from '../shared/shared-dialog-forms/confirm-delete-form/confirm-delete-form.component';
import {AppErrorHandler} from './app-error-handler';
import {MessageService} from 'primeng/api';

@NgModule({
  imports: [
    SharedModule,
    SharedDialogFormsModule,
  ],
  exports: [
    NavBarComponent
  ],
  declarations: [
    NavBarComponent
  ],
  entryComponents: [
    ConfirmDeleteFormComponent
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
