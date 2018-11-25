import {Injectable} from '@angular/core';
import {Role, User} from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor() {
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('isLoggedIn');
  }

  getCurrentUser(): User {
    return {
      id: 4,
      username: 'user1',
      role: localStorage.getItem('role') as Role,
      name: 'Test User1',
      yearStarted: 2018,
      semester: 1,
      subjects: [
        {
          id: 1,
          name: 'Fordítóprogramok',
          day: 'Hétfő',
          time: '16:30',
          location: 'D-00.810'
        },
        {
          id: 2,
          name: 'Alkalmazások fejlesztése',
          day: 'Hétfő',
          time: '18:30',
          location: 'D-00-810'
        }
      ]
    };
  }
}
