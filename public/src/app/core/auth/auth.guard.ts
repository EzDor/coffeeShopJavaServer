import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {CoreModule} from '../core.module';
import {AuthenticationService} from './auth.service';
import {Constants} from '../../model/constants';

@Injectable({
  providedIn: CoreModule
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private authenticationService: AuthenticationService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (localStorage.getItem(Constants.CURRENT_USER)) {
      // logged in so return true
      this.authenticationService.updateLoginOnCache();
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate([Constants.SIGN_IN_COMPONENT_PATH],
      {
        queryParams: {returnUrl: state.url}
      });
    return false;
  }
}
