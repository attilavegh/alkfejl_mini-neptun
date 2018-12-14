import {Component, Input, OnInit} from '@angular/core';

import {BehaviorSubject, Observable} from 'rxjs';
import {catchError, switchMap, tap} from 'rxjs/operators';

import {SubjectService} from '../../../../services/subject/subject.service';
import {ExtendedSubject, Subject} from '../../../../models/subject.model';
import {StudentService} from '../../../../services/student/student.service';
import {Student} from '../../../../models/user.model';
import {ConfirmationDialogService} from '../../../../services/confirmation-dialog/confirmation-dialog.service';

@Component({
  selector: 'app-student-subject-management',
  templateUrl: './student-subject-management.component.html',
  styleUrls: ['./student-subject-management.component.scss']
})
export class StudentSubjectManagementComponent implements OnInit {

  subjects$: Observable<ExtendedSubject[]>;
  refreshSubjects$ = new BehaviorSubject<boolean>(true);

  displayedColumns: string[] = ['id', 'name', 'day', 'time', 'location', 'teacher', 'isTaken', 'options'];
  @Input() currentUser: Student;

  constructor(private subjectService: SubjectService,
              private studentService: StudentService,
              private dialog: ConfirmationDialogService) {
  }

  ngOnInit() {
    this.getSubjects();
  }

  onAdd(subject: Subject) {
    this.currentUser.subjects.push(subject);
    this.studentService.manageStudentSubject(this.currentUser).pipe(
      tap(() => this.refreshSubjects$.next(true)),
      catchError(() => this.dialog.open({title: 'Hiba a tárgy felvétele közben', closeText: 'Bezár'}))
    ).subscribe();
  }

  onRemove(subject: Subject) {
    const selectedSubject = this.currentUser.subjects.find(s => s.id === subject.id);
    const selectedSubjectIndex = this.currentUser.subjects.indexOf(selectedSubject);
    this.currentUser.subjects.splice(selectedSubjectIndex, 1);

    this.studentService.manageStudentSubject(this.currentUser).pipe(
      tap(() => this.refreshSubjects$.next(true)),
      catchError(() => this.dialog.open({title: 'Hiba a tárgy leadása közben', closeText: 'Bezár'}))
    ).subscribe();
  }

  private getSubjects() {
    this.subjects$ = this.refreshSubjects$.pipe(
      switchMap(() => this.subjectService.getAllSubjects())
    );
  }
}
