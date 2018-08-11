import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {SharedModule} from '../shared/shared.module';
import {RegisterComponent} from './register.component';
import {SignInComponent} from './sign-in.component';
import {LoginLogoComponent} from './login-logo.component';

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild([
      {path: 'register', component: RegisterComponent},
      {path: 'sign-in', component: SignInComponent},
    ])
  ],
  declarations: [
    RegisterComponent,
    SignInComponent,
    LoginLogoComponent
  ],
  exports: []
})

export class LoginModule {
}
