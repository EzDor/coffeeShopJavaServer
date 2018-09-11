import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {CoreModule} from '../core.module';
import {AuthenticationService} from '../services/auth.service';

@Injectable({
  providedIn: CoreModule
})
export class AdminGuard implements CanActivate {

  constructor(private router: Router, private authenticationService: AuthenticationService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.authenticationService.isUserAdmin) {
      return true;
    }
    else {
      this.router.navigate(['']);
    }

  }
}
