import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {ExtendedSubject, Subject} from '../../models/subject.model';
import {environment} from '../../../environments/environment';
import {AuthorizationService} from '../authorization/authorization.service';
import {User} from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  constructor(private http: HttpClient,
              private auth: AuthorizationService) {
  }

  getAllSubjects(): Observable<ExtendedSubject[]> {
    return this.http.get<Subject[]>(environment.apiUrl + 'subject').pipe(
      map((subjects: Subject[]) => this.markTakenSubjects(subjects))
    );
  }

  getSubjectById(id: number): Observable<Subject> {
    return this.http.get<Subject>(environment.apiUrl + 'subject/' + id);
  }

  addSubject(subject: Subject): Observable<Subject> {
    return this.http.post<Subject>(environment.apiUrl + 'subject', subject);
  }

  modifySubject(subject: Subject): Observable<Subject> {
    return this.http.put<Subject>(environment.apiUrl + 'subject/' + subject.id, subject);
  }

  deleteSubject(id: number): Observable<Subject> {
    return this.http.delete<Subject>(environment.apiUrl + 'subject/' + id);
  }

  private markTakenSubjects(subjects: Subject[]): ExtendedSubject[] {
    const currentUser: User = this.auth.user;
    const extendedSubjects: ExtendedSubject[] = [];

    subjects.forEach((subject: Subject) => {
      if (currentUser.subjects.some(sub => sub.id === subject.id)) {
        extendedSubjects.push({...subject, isTaken:  true});
      } else {
        extendedSubjects.push({...subject, isTaken:  false});
      }
    });

    return extendedSubjects;
  }
}
