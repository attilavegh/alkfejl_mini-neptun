import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Subject} from '../../models/subject.model';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) {
  }

  getTimetable(username: string): Observable<Subject[]> {
    return this.http.get<Subject[]>(environment.apiUrl + 'students/' + username + '/subjects');
  }
}
