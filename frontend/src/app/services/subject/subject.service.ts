import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {map, tap} from 'rxjs/operators';

import {ExtendedSubject, Subject} from '../../models/subject.model';
import {environment} from '../../../environments/environment';
import {AuthorizationService} from '../authorization/authorization.service';
import {User} from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  private cache: Subject[];

  constructor(private http: HttpClient,
              private authService: AuthorizationService) {
  }

  getAllSubjects(): Observable<ExtendedSubject[]> {
    return this.http.get<Subject[]>(environment.apiUrl + 'subjects').pipe(
      tap((subjects: Subject[]) => this.setCache(subjects)),
      map((subjects: Subject[]) => this.markTakenSubjects(subjects))
    );
  }

  getTimetable(username: string): Observable<Subject[]> {
    return this.http.get<Subject[]>(environment.apiUrl + 'students/' + username + '/subjects');
  }

  addSubject(teacherId: number, subject: Subject): Observable<Subject> {
    return this.http.post<Subject>(environment.apiUrl + 'subjects/' + teacherId, subject);
  }

  modifySubject(subject: Subject): Observable<Subject> {
    return this.http.put<Subject>(environment.apiUrl + 'subjects/' + subject.id, subject);
  }

  deleteSubject(id: number): Observable<Subject> {
    return this.http.delete<Subject>(environment.apiUrl + 'subjects/' + id);
  }

  getAllSubjectByIdFromCache(id: number): Subject {
    if (this.cache) {
      return this.cache.find(item => item.id === id);
    }
  }

  private markTakenSubjects(subjects: Subject[]): ExtendedSubject[] {
    const currentUser: User = this.authService.getCurrentUser();
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

  private setCache(subjects: Subject[]) {
    this.cache = subjects;
  }
}
