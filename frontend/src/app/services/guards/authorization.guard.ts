import {Injectable} from '@angular/core';
import {CanActivate, CanActivateChild, Router} from '@angular/router';
import {AuthorizationService} from '../authorization/authorization.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard implements CanActivateChild {

  constructor(private router: Router,
              private auth: AuthorizationService) {
  }

  canActivateChild(): Observable<boolean> | boolean{
    if (this.auth.isLoggedIn() && this.auth.user) {
      return true;
    }

    if (this.auth.isLoggedIn() && !this.auth.user) {
      const decodedToken = atob(localStorage.getItem('token'));
      const credentials = decodedToken.split(':');

      return this.auth.login(credentials[0], credentials[1]).pipe(
        map(user => !!user)
      );
    }

    this.router.navigate(['/login']);
    return false;
  }
}
