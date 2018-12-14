import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';

import {NewStudent, NewTeacher, Student, Teacher} from '../../models/user.model';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  registerStudent(form: NewStudent): Observable<Student> {
    return this.http.post<Student>(environment.apiUrl + 'student/register', form);
  }

  registerTeacher(form: NewTeacher): Observable<Teacher> {
    return this.http.post<Teacher>(environment.apiUrl + 'teacher/register', form);
  }

  getUser(username: string, token: string): Observable<Student | Teacher> {
    return this.http.get<Student | Teacher>(environment.apiUrl + 'user/' + username, {
      headers: {'Authorization': `Basic ${token}`}
    });
  }
}
