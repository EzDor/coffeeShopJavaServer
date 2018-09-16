import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Constants} from 'src/app/models/constants';
import {User} from 'src/app/models/user/user';
import {Observable} from 'rxjs';
import {CoreModule} from 'src/app/core/core.module';
import {UpdateUser} from 'src/app/models/user/update-user';


@Injectable({
  providedIn: CoreModule
})
export class UserService {

  private readonly apiPrefix: string;

  constructor(private http: HttpClient) {
    this.apiPrefix = Constants.BASE_URL + Constants.API_PREFIX;
  }

  public createUser(user: User) {
    return this.http.post(this.apiPrefix + Constants.SIGN_UP_API_CALL, user);
  }

  public get users(): Observable<User[]> {
    return this.http.get<User[]>(this.apiPrefix + Constants.GET_USERS_API_CALL);
  }

  public updateUser(user: UpdateUser) {
    return this.http.post(this.apiPrefix + Constants.UPDATE_USER_API_CALL, user);
  }

  public deleteUser(username: string) {
    return this.http.post(this.apiPrefix + Constants.DELETE_USER_API_CALL, username);
  }

}
