import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '@services/auth.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Constants} from '@models/constants';


@Component({
  selector: 'app-nav-bar',
  styleUrls: [`./nav-bar.component.css`],
  templateUrl: './nav-bar.component.html'
})

export class NavBarComponent implements OnInit {

  public isAdmin$: Observable<boolean>;
  public isLoggedIn$: Observable<boolean>;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }


  ngOnInit() {
    this.isAdmin$ = this.authenticationService.isUserAdmin;
    this.isLoggedIn$ = this.authenticationService.isUserLoggedIn;
  }

  public logout() {
    this.authenticationService.logout();
  }

  public redirectToAdminPage() {
    this.router.navigate([Constants.ADMIN_COMPONENT_PATH]);
  }

  public redirectToHomePage() {
    this.router.navigate([Constants.HOME_PATH]);
  }

  public redirectToCartPage() {
    this.router.navigate([Constants.CART_COMPONENT_PATH]);
  }
}
