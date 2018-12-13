import {Component, OnInit} from '@angular/core';

import {Observable} from 'rxjs';

import {Subject} from '../../models/subject.model';
import {StudentService} from '../../services/student/student.service';
import {AuthorizationService} from '../../services/authorization/authorization.service';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.scss']
})
export class TimetableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'day', 'time', 'location'];
  subjects$: Observable<Subject[]>;

  constructor(private studentService: StudentService,
              private auth: AuthorizationService) {
  }

  ngOnInit() {
    this.subjects$ = this.studentService.getTimetable(this.auth.user.username);
  }
}
