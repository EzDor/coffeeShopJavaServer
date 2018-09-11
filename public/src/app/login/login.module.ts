import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {SharedModule} from '../shared/shared.module';
import {RegisterComponent} from './register.component';
import {SignInComponent} from './sign-in.component';
import {LoginLogoComponent} from './login-logo.component';
import {Constants} from '../models/constants';

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild([
      {path: Constants.REGISTER_COMPONENT_PATH, component: RegisterComponent},
      {path: Constants.SIGN_IN_COMPONENT_PATH, component: SignInComponent},
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
