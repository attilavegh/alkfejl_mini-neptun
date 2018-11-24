import { Component, OnInit } from '@angular/core';
import {Role, User} from '../../models/user.model';
import {AuthorizationService} from '../../services/authorization/authorization.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  role = Role;
  currentUser: User;

  constructor(private auth: AuthorizationService) {
    this.currentUser = this.auth.getCurrentUser();
  }

  ngOnInit() {
  }
}
