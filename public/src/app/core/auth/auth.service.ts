import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Constants} from '../../model/constants';
import {BehaviorSubject} from 'rxjs';


@Injectable()
export class AuthenticationService {

  private loggedIn: BehaviorSubject<boolean>;

  constructor(private http: HttpClient) {
    this.loggedIn = new BehaviorSubject<boolean>(false);
  }

  public login(loginDetails: LoginDetails) {
    return this.http.post<any>(Constants.BASE_URL + Constants.API_PREFIX + Constants.LOGIN_API_CALL, {
      username: loginDetails.username,
      password: loginDetails.password
    })
      .pipe(map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem(Constants.CURRENT_USER, JSON.stringify(user));
          this.loggedIn.next(true);
        }

        return user;
      }));
  }

  public logout(): void {
    // remove user from local storage to log user out
    localStorage.removeItem(Constants.CURRENT_USER);
    if (this.isLoggedIn) {
      this.loggedIn.next(false);
      location.reload(true);
    }
  }


  public get isLoggedIn(): BehaviorSubject<boolean> {
    return this.loggedIn;
  }

  public updateLoginOnCache(): void {
    this.loggedIn.next(true);
  }
}

export interface LoginDetails {
  username: string;
  password: string;
}
