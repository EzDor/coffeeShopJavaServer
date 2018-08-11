import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AuthGuard} from './core/auth/auth.guard';

export const appRoutes: Routes = [
  {path: 'login', loadChildren: './login/login.module#LoginModule'},
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},

  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];
