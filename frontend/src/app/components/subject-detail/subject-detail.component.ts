import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';

import {SubjectService} from '../../services/subject/subject.service';
import {Subject} from '../../models/subject.model';
import {AuthorizationService} from '../../services/authorization/authorization.service';

@Component({
  selector: 'app-subject-detail',
  templateUrl: './subject-detail.component.html',
  styleUrls: ['./subject-detail.component.scss']
})
export class SubjectDetailComponent implements OnInit {

  subjectForm = this.formBuilder.group({
    name: [null, Validators.required],
    day: [null, Validators.required],
    time: [null, Validators.required],
    location: [null, Validators.required]
  });

  selectedSubject: Subject;

  constructor(private subjectService: SubjectService,
              private auth: AuthorizationService,
              private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.assignSubjectOnEdit();
  }

  onSubmit() {
    if (this.selectedSubject) {
      this.subjectService.modifySubject(this.createModifiedSubject()).subscribe();
    } else {
      this.subjectService.addSubject(this.auth.user.id, this.subjectForm.value).subscribe();
    }
  }

  private assignSubjectOnEdit() {
    this.getSubject();

    if (this.selectedSubject) {
      this.subjectForm.get('name').setValue(this.selectedSubject.name);
      this.subjectForm.get('day').setValue(this.selectedSubject.day);
      this.subjectForm.get('time').setValue(this.selectedSubject.time);
      this.subjectForm.get('location').setValue(this.selectedSubject.location);
    } else {
      this.router.navigate(['/subjects/new']);
    }
  };

  private getSubject() {
    const subjectId = parseInt(this.route.snapshot.paramMap.get('id'));

    if (subjectId) {
      this.selectedSubject = this.subjectService.getAllSubjectByIdFromCache(subjectId);
    }
  }

  private createModifiedSubject(): Subject {
    return {
      ...this.subjectForm.value,
      id: this.selectedSubject.id
    };
  }
}
