import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class StudentGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    const role = localStorage.getItem('role');

    if (role !== 'ROLE_STUDENT') {
      this.router.navigate(['subjects']);
      return false;
    }

    return true;
  }
}
