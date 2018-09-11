import {User} from './user.model';

export interface UpdateUser {
  usernameToUpdate: string;
  password: string;
  updatedUserDetails: User;

}
