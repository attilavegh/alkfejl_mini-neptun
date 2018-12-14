import {Injectable} from '@angular/core';

import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

import {Role, Student, Teacher} from '../../models/user.model';
import {UserService} from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private currentUser: Student | Teacher;

  constructor(private userService: UserService) {
  }

  login(username: string, password: string): Observable<Student | Teacher> {
    const token = btoa(username + ':' + password);

    return this.userService.getUser(username, token).pipe(tap(user => this.onLogin(user, username, password)));
  }

  private onLogin(user: Student | Teacher, username: string, password: string) {
    localStorage.setItem('token', btoa(username + ':' + password));
    localStorage.setItem('role', user.role);
    this.currentUser = user;
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  isStudent(): boolean {
    return this.currentUser && this.currentUser.role === Role.STUDENT;
  }

  isTeacher(): boolean {
    return this.currentUser && this.currentUser.role === Role.TEACHER;
  }

  get user(): Student | Teacher {
    return this.currentUser;
  }

  set user(user: Student | Teacher) {
    this.currentUser = user;
  }
}
