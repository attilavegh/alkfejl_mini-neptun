import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {AuthorizationService} from '../../services/authorization/authorization.service';
import {catchError, tap} from 'rxjs/operators';
import {of} from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm = this.formBuilder.group({
    username: [null, Validators.required],
    password: [null, Validators.required],
  });

  isLoginFailed = false;

  constructor(private formBuilder: FormBuilder,
              private auth: AuthorizationService,
              private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.auth.login(this.username, this.password).pipe(
      tap(() => this.router.navigate(['subjects'])),
      catchError(() => {
        this.isLoginFailed = true;
        return of();
      })
    ).subscribe();
  }

  get username(): string {
    return this.loginForm.get('username').value;
  }

  get password(): string {
    return this.loginForm.get('password').value;
  }
}
