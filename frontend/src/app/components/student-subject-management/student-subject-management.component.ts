import {Component, OnInit} from '@angular/core';

import {Observable} from 'rxjs';

import {SubjectService} from '../../services/subject/subject.service';
import {ExtendedSubject} from '../../models/subject.model';

@Component({
  selector: 'app-student-subject-management',
  templateUrl: './student-subject-management.component.html',
  styleUrls: ['./student-subject-management.component.scss']
})
export class StudentSubjectManagementComponent implements OnInit {

  subjects$: Observable<ExtendedSubject[]>;
  displayedColumns: string[] = ['id', 'name', 'day', 'time', 'location', 'isTaken', 'options'];

  constructor(private subjectService: SubjectService) {
  }

  ngOnInit() {
   this.subjects$ = this.subjectService.getAllSubjects();
  }

  onAdd(id: number) {
    console.log(id);
  }

  onRemove(id: number) {
    console.log(id);
  }
}
