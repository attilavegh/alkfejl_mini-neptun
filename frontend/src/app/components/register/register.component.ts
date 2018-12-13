import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {Params, Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';

import {catchError, map} from 'rxjs/operators';
import {of} from 'rxjs';

import {NewStudent, NewTeacher, Role} from '../../models/user.model';
import {UserService} from '../../services/user/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  role = Role;
  registrationType: Role = Role.STUDENT;

  registrationError = false;
  registrationErrorMessage: string;

  constructor(private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
  }

  switchToStudentRegistration() {
    this.resetError();
    this.registrationType = Role.STUDENT;
  }

  switchToTeacherRegistration() {
    this.resetError();
    this.registrationType = Role.TEACHER;
  }

  onStudentRegister(student: NewStudent) {
    const queryParams: Params = { queryParams: { username: student.username } };

    this.userService.registerStudent(student).pipe(
      map(() => this.router.navigate(['/login'], queryParams)),
      catchError((error: HttpErrorResponse) => this.checkRegistrationError(error))
    ).subscribe();
  }

  onTeacherRegister(teacher: NewTeacher) {
    const queryParams: Params = { queryParams: { username: teacher.username } };

    this.userService.registerTeacher(teacher).pipe(
      map(() => this.router.navigate(['/login'], queryParams)),
      catchError((error: HttpErrorResponse) => this.checkRegistrationError(error))
    ).subscribe();
  }

  onMatchingPasswords() {
    this.registrationError = true;
    this.registrationErrorMessage = 'A jelszavak megegyeznek';
  }

  private checkRegistrationError(error: HttpErrorResponse) {
    if (error.status === 400) {
      this.registrationError = true;
      this.registrationErrorMessage = 'A felhasználónév létezik';
    } else {
      this.registrationError = true;
      this.registrationErrorMessage = 'Hiba a regisztráció közben';
    }

    return of();
  }

  private resetError() {
    this.registrationError = false;
    this.registrationErrorMessage = '';
  }
}
