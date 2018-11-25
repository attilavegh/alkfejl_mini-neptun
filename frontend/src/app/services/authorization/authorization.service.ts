import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';

import {Role, Student, Teacher} from '../../models/user.model';
import {environment} from '../../../environments/environment';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private currentUser: Student | Teacher;

  constructor(private client: HttpClient) {
  }

  login(username: string, password: string): Observable<Student | Teacher> {
    const token = btoa(username + ':' + password);

    return this.client.get<Student | Teacher>(environment.apiUrl + 'users/' + username, {
      headers: {'Authorization': `Basic ${token}`}
    }).pipe(tap(user => this.onLogin(user, username, password)));
  }

  private onLogin(user: Student | Teacher, username: string, password: string) {
    localStorage.setItem('token', btoa(username + ':' + password));
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
