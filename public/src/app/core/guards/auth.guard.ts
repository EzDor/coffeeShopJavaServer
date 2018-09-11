import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {CoreModule} from '../core.module';
import {AuthenticationService} from '../services/auth.service';
import {Constants} from '../../models/constants';
import {LoginResponseParams} from '../../models/login/login-response-params.model';

@Injectable({
  providedIn: CoreModule
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private authenticationService: AuthenticationService) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const isUserAuthenticated: boolean = this.authenticationService.isUserAuthenticated();
    if (!isUserAuthenticated) {
      this.router.navigate([Constants.SIGN_IN_COMPONENT_FULL_PATH],
        {
          queryParams: {returnUrl: state.url}
        });
      return false;
    }
    return true;
  }
}
