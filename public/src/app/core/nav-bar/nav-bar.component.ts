import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../auth/auth.service';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';


@Component({
  selector: 'app-nav-bar',
  styleUrls: [`./nav-bar.component.css`],
  templateUrl: './nav-bar.component.html'
})

export class NavBarComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  isLoggedIn$: Observable<boolean>;

  ngOnInit() {
    this.isLoggedIn$ = this.authenticationService.isLoggedIn;
  }

  onLogout() {
    this.authenticationService.logout();
  }
}
