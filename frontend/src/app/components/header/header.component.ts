import {Component} from '@angular/core';

import {AuthorizationService} from '../../services/authorization/authorization.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(private auth: AuthorizationService) {
  }

  onLogout() {
    localStorage.clear();
    this.auth.user = null;
  }
}
