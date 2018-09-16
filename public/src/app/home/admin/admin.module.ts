import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {RouterModule} from '@angular/router';
import {AdminGuard} from '../../core/guards/admin.guard';
import {AdminPageComponent} from './admin-page.component';
import {AdminDataTableComponent} from './admin-data-table.component';
import {SharedDialogFormsModule} from '../../shared/shared-dialog-forms/shared-dialog-forms.module';
import {AdminService} from '../../core/services/admin.service';

@NgModule({
  imports: [
    SharedModule,
    SharedDialogFormsModule,
    RouterModule.forChild([
      {path: '', component: AdminPageComponent, canActivate: [AdminGuard]}
    ])
  ],
  declarations: [
    AdminPageComponent,
    AdminDataTableComponent,
  ],
  entryComponents: [
  ]
})
export class AdminModule {
}
