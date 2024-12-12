import {UserRole} from './user-role.enum';
import {AuthProvider} from './auth-provider.enum';

export interface User {
  username: string;
  password: string;
  role: UserRole;
  provider: AuthProvider;
}
