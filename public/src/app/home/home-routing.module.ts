import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from '@app/home/home.component';
import {Constants} from '@models/constants';
import {AuthGuard} from '@core/guards/auth.guard';
import {CartComponent} from '@app/home/cart/cart.component';

const routes: Routes = [
  {path: Constants.HOME_PATH, component: HomeComponent, canActivate: [AuthGuard]},
  {path: Constants.CART_COMPONENT_PATH, component: CartComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
