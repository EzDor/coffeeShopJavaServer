import {UserStatus} from './user-status.enum';

export interface User {
  firstName: string;
  lastName: string;
  username: string;
  admin: boolean;
  status: UserStatus;
  password?: string;
}
