import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';

import {catchError, filter, map, take} from 'rxjs/operators';

import {SubjectService} from '../../../../services/subject/subject.service';
import {Subject} from '../../../../models/subject.model';
import {ConfirmationDialogService} from '../../../../services/confirmation-dialog/confirmation-dialog.service';

@Component({
  selector: 'app-subject-detail',
  templateUrl: './subject-detail.component.html',
  styleUrls: ['./subject-detail.component.scss']
})
export class SubjectDetailComponent implements OnInit {

  subjectForm = this.formBuilder.group({
    name: ['', Validators.required],
    day: ['', Validators.required],
    time: ['', Validators.required],
    location: ['', Validators.required]
  });

  private subjectId: number;

  constructor(private subjectService: SubjectService,
              private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder,
              private dialog: ConfirmationDialogService) {
  }

  ngOnInit() {
    const urlSubjectParam = this.getUrlParam();

    if (urlSubjectParam) {
      this.initModification(urlSubjectParam).subscribe();
    } else {
      this.fallbackToNewSubject();
    }
  }

  onSubmit() {
    if (this.subjectId) {
      this.subjectService.modifySubject(this.createModifiedSubject()).pipe(
        map(() => this.backToSubjects()),
        catchError(() => {
          this.backToSubjects();
          return this.dialog.open({title: 'Hiba a tárgy modosítása közben.', closeText: 'Bezárás'});
        })
      ).subscribe();
    } else {
      this.subjectService.addSubject(this.subjectForm.value).pipe(
        map(() => this.backToSubjects()),
        catchError(() => this.dialog.open({title: 'Hiba a tárgy modosítása közben.', closeText: 'Bezárás'}))
      ).subscribe();
    }
  }

  private getUrlParam() {
    return parseInt(this.route.snapshot.paramMap.get('id'), 10);
  }

  private initModification(subjectId: number) {
    return this.subjectService.getSubjectById(subjectId).pipe(
      take(1),
      filter((subject: Subject) => !!subject),
      map((subject: Subject) => this.fillSubjectForm(subject)),
      catchError(() => this.fallbackToNewSubject())
    );
  }

  private fillSubjectForm(subject: Subject) {
    this.subjectId = subject.id;
    this.subjectForm.get('name').setValue(subject.name);
    this.subjectForm.get('day').setValue(subject.day);
    this.subjectForm.get('time').setValue(subject.time);
    this.subjectForm.get('location').setValue(subject.location);
  }

  private fallbackToNewSubject() {
    return this.router.navigate(['/subjects/new']);
  }

  private backToSubjects() {
    return this.router.navigate(['subjects']);
  }

  private createModifiedSubject(): Subject {
    return {
      ...this.subjectForm.value,
      id: this.subjectId
    };
  }
}
