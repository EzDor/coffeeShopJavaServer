import {User} from './user';

export interface UpdateUser {
  usernameToUpdate: string;
  password: string;
  updatedUserDetails: User;

}
