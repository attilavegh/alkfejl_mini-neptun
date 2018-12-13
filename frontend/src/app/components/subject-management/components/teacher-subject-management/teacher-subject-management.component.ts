import {Component, OnInit} from '@angular/core';

import {BehaviorSubject, Observable} from 'rxjs';
import {catchError, switchMap, tap} from 'rxjs/operators';

import {Subject} from '../../../../models/subject.model';
import {SubjectService} from '../../../../services/subject/subject.service';
import {ConfirmationDialogService} from '../../../../services/confirmation-dialog/confirmation-dialog.service';

@Component({
  selector: 'app-teacher-subject-management',
  templateUrl: './teacher-subject-management.component.html',
  styleUrls: ['./teacher-subject-management.component.scss']
})
export class TeacherSubjectManagementComponent implements OnInit {

  subjects$: Observable<Subject[]>;
  refreshSubjects$ = new BehaviorSubject<boolean>(true);

  displayedColumns: string[] = ['id', 'name', 'day', 'time', 'location', 'options'];

  constructor(private subjectService: SubjectService,
              private dialog: ConfirmationDialogService) {
  }

  ngOnInit() {
    this.getSubjects();
  }

  onDelete(id: number) {
    this.dialog.open({
      title: 'Biztosan törli a tárgyat?',
      closeText: 'Nem',
      hasConfirmationAction: true,
      confirmationActionText: 'Igen'
    }).subscribe(() => {
      this.subjectService.deleteSubject(id).pipe(
        tap(() => this.refreshSubjects$.next(true)),
        catchError(() => this.dialog.open({title: 'Hiba a tárgy törlése közben', closeText: 'Bezárás'}))
      ).subscribe();
    });
  }

  private getSubjects() {
    this.subjects$ = this.refreshSubjects$.pipe(
      switchMap(() => this.subjectService.getAllSubjectsForTeacher())
    );
  }
}
