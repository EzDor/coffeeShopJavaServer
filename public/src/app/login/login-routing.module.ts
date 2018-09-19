import {RouterModule, Routes} from '@angular/router';
import {Constants} from '@models/constants';
import {NgModule} from '@angular/core';
import {RegisterComponent} from '@app/login/register.component';
import {SignInComponent} from '@app/login/sign-in.component';

const routes: Routes = [
  {path: Constants.REGISTER_COMPONENT_PATH, component: RegisterComponent},
  {path: Constants.SIGN_IN_COMPONENT_PATH, component: SignInComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class LoginRoutingModule {
}
