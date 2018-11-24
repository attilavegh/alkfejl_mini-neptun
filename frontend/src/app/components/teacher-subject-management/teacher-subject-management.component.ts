import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ExtendedSubject} from '../../models/subject.model';
import {SubjectService} from '../../services/subject/subject.service';

@Component({
  selector: 'app-teacher-subject-management',
  templateUrl: './teacher-subject-management.component.html',
  styleUrls: ['./teacher-subject-management.component.scss']
})
export class TeacherSubjectManagementComponent implements OnInit {

  subjects$: Observable<ExtendedSubject[]>;
  displayedColumns: string[] = ['id', 'name', 'day', 'time', 'location', 'options'];

  constructor(private subjectService: SubjectService) {
  }

  ngOnInit() {
    this.subjects$ = this.subjectService.getAllSubjects();
  }

  onDelete(id: number) {
    this.subjectService.deleteSubject(id).subscribe();
  }
}
