import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';

import {Subject} from '../../models/subject.model';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(private http: HttpClient) { }

  getAllSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(environment.apiUrl + 'teacher/subjects');
  }
}
