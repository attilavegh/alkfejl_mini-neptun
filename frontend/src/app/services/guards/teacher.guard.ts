import {Injectable} from '@angular/core';
import {CanActivate} from '@angular/router';

import {AuthorizationService} from '../authorization/authorization.service';
import {Role} from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class TeacherGuard implements CanActivate {

  constructor(private auth: AuthorizationService) {
  }

  canActivate(): boolean {
    return this.auth.getCurrentUser().role === Role.TEACHER;
  }
}
