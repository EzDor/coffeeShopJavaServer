import {NgModule} from '@angular/core';
import {SharedModule} from '@shared/shared.module';
import {RegisterComponent} from './register.component';
import {SignInComponent} from './sign-in.component';
import {LoginLogoComponent} from './login-logo.component';
import {LoginRoutingModule} from '@app/login/login-routing.module';

@NgModule({
  imports: [
    SharedModule,
    LoginRoutingModule,
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
