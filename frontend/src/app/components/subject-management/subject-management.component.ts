import { Component, OnInit } from '@angular/core';
import {Role, User} from '../../models/user.model';
import {AuthorizationService} from '../../services/authorization/authorization.service';

@Component({
  selector: 'app-subject-management',
  templateUrl: './subject-management.component.html',
  styleUrls: ['./subject-management.component.scss']
})
export class SubjectManagementComponent implements OnInit {

  role = Role;
  currentUser: User;

  constructor(private auth: AuthorizationService) {
    this.currentUser = this.auth.user;
  }

  ngOnInit() {
  }
}
