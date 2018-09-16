import {Routes} from '@angular/router';
import {AuthGuard} from './core/guards/auth.guard';
import {Constants} from './models/constants';
import {AdminGuard} from './core/guards/admin.guard';

export const appRoutes: Routes = [
  {path: Constants.LOGIN_PATH, loadChildren: Constants.LOGIN_LAZY_MODULE_PATH},
  {path: Constants.HOME_PATH, loadChildren: Constants.HOME_LAZY_MODULE_PATH, canActivate: [AuthGuard]},
  {path: Constants.ADMIN_COMPONENT_PATH, loadChildren: Constants.ADMIN_LAZY_MODULE_PATH, canActivate: [AuthGuard, AdminGuard]},

  // otherwise redirect to home
  {path: Constants.OTHERWISE_PATH, redirectTo: Constants.HOME_PATH}
];
