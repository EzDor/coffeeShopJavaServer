import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';

export const appRoutes: Routes = [
  {path: 'login', loadChildren: './login/login.module#LoginModule'},
  {path: 'home', component: HomeComponent}
];
