import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {NewStudent, NewTeacher, Student, Teacher} from '../../models/user.model';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  registerStudent(form: NewStudent) {
    return this.http.post<Student>(environment.apiUrl + 'students/register', form);
  }

  registerTeacher(form: NewTeacher) {
    return this.http.post<Teacher>(environment.apiUrl + 'teachers/register', form);
  }
}
