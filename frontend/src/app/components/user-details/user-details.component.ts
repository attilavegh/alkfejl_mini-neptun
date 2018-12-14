import { Component, OnInit } from '@angular/core';

import {AuthorizationService} from '../../services/authorization/authorization.service';
import {Student, Teacher} from '../../models/user.model';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  currentUser: Student | Teacher;

  constructor(private auth: AuthorizationService) {}

  ngOnInit() {
    this.currentUser = this.auth.user;
  }
}
