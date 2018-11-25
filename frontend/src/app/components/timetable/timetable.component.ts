import {Component, OnInit} from '@angular/core';
import {Subject} from '../../models/subject.model';
import {Observable} from 'rxjs';
import {StudentService} from '../../services/student/student.service';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.scss']
})
export class TimetableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'day', 'time', 'location'];
  subjects$: Observable<Subject[]>;

  constructor(private studentService: StudentService) {
  }

  ngOnInit() {
    this.subjects$ = this.studentService.getTimetable('user1');
  }
}
