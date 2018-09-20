import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {BehaviorSubject} from 'rxjs';
import {LoginRequestParams} from '../../models/login/login-request-params.model';
import {LoginResponseParams} from '../../models/login/login-response-params.model';
import {CoreModule} from '../core.module';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Constants} from '../../models/constants';
import {StorageService} from './storage.service';


@Injectable({
    providedIn: CoreModule
  }
)
export class AuthenticationService {

  public isLoggedIn: BehaviorSubject<boolean>;
  public isAdmin: BehaviorSubject<boolean>;

  private jwtHelperService: JwtHelperService;

  constructor(private http: HttpClient, private storageService: StorageService) {
    this.isLoggedIn = new BehaviorSubject<boolean>(false);
    this.isAdmin = new BehaviorSubject<boolean>(false);
    this.jwtHelperService = new JwtHelperService();
  }

  public login(loginDetails: LoginRequestParams) {
    return this.http.post<any>(Constants.BASE_URL + Constants.API_PREFIX + Constants.LOGIN_API_CALL, {
      username: loginDetails.username,
      password: loginDetails.password
    })
      .pipe(map((user: LoginResponseParams) => {
        // login successful if there's a jwt token in the response
        if (user && user.token) {
          // store component details and jwt token in local storage to keep component logged in between page refreshes
          localStorage.setItem(Constants.CURRENT_USER, JSON.stringify(user));
          this.isLoggedIn.next(true);
          this.isAdmin.next(this.isAdminRoleIncluded(user.token));
        }

        return user;
      }));
  }

  public logout(): void {
    // remove component from local storage to log component out
    localStorage.removeItem(Constants.CURRENT_USER);
    if (this.isLoggedIn.value) {
      this.isLoggedIn.next(false);
      this.isAdmin.next(false);
      location.reload(true);
    }
  }


  public get isUserLoggedIn(): BehaviorSubject<boolean> {
    return this.isLoggedIn;
  }

  public get isUserAdmin(): BehaviorSubject<boolean> {
    return this.isAdmin;
  }

  public updateAuthenticationParams(token: string): void {
    this.isLoggedIn.next(true);
    this.isAdmin.next(this.isAdminRoleIncluded(token));
  }

  public isUserAuthenticated(): boolean {
    const userOnStorage: LoginResponseParams = this.storageService.getStorageData(Constants.CURRENT_USER);
    if (userOnStorage && !this.jwtHelperService.isTokenExpired(userOnStorage.token)) {
      this.updateAuthenticationParams(userOnStorage.token);
      return true;
    }
    return false;
  }

  private isAdminRoleIncluded(token): boolean {
    const decodedJwtData = this.jwtHelperService.decodeToken(token);
    return decodedJwtData.roles === Constants.ROLE_ADMIN;
  }
}
