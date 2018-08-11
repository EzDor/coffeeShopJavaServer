import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Constants} from '../model/constants';
import {User} from '../model/user.model';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {
  }

  getUserById(username: string) {
    return this.http.get<User>(Constants.BASE_URL + '/' + username);
  }

  public createUser(user: User) {
    return this.http.post(Constants.BASE_URL + Constants.API_PREFIX + Constants.SIGN_UP_API_CALL, user);
  }

  updateUser(user: User) {
    return this.http.put(Constants.BASE_URL + '/' + user.id, user);
  }

  deleteUser(id: number) {
    return this.http.delete(Constants.BASE_URL + '/' + id);
  }
}
