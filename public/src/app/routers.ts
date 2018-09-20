import {Routes, RouterModule} from '@angular/router';
import {AuthGuard} from '@core/guards/auth.guard';
import {Constants} from '@models/constants';
import {AdminGuard} from '@core/guards/admin.guard';
import {NgModule} from '@angular/core';
import {NotFoundComponent} from '@app/shell/not-found/not-found.component';

export const routes: Routes = [
  {path: Constants.LOGIN_PATH, loadChildren: Constants.LOGIN_LAZY_MODULE_PATH},
  {path: Constants.HOME_PATH, loadChildren: Constants.HOME_LAZY_MODULE_PATH, canActivate: [AuthGuard]},
  {path: Constants.ADMIN_COMPONENT_PATH, loadChildren: Constants.ADMIN_LAZY_MODULE_PATH, canActivate: [AuthGuard, AdminGuard]},

  // otherwise redirect to home
  {path: Constants.OTHERWISE_PATH, component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutes {
}
