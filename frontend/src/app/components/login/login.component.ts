import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';

import {AuthorizationService} from '../../services/authorization/authorization.service';
import {catchError, filter, map, take, tap} from 'rxjs/operators';
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
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.setUsername().subscribe();
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

  private setUsername() {
    return this.route.queryParamMap.pipe(
      take(1),
      map((params: ParamMap) => params.get('username')),
      filter((param: string) => !!param),
      tap((param: string) => {
        this.loginForm.get('username').setValue(param);
      })
    );
  }
}
