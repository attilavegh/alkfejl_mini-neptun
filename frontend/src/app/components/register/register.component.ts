import {Component, OnInit} from '@angular/core';

import {Role, Student, Teacher} from '../../models/user.model';
import {FormGroup} from '@angular/forms';

export function checkPasswords(group: FormGroup) {
  const pass = group.controls.password.value;
  const confirmPass = group.controls.passwordConfirm.value;

  return pass === confirmPass ? null : { notSame: true };
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  role = Role;
  registrationType: Role = Role.STUDENT;

  constructor() {
  }

  ngOnInit() {
  }

  switchToStudentRegistration() {
    this.registrationType = Role.STUDENT;
  }

  switchToTeacherRegistration() {
    this.registrationType = Role.TEACHER;
  }

  onStudentRegister(student: Student) {
    console.log(student);
  }

  onTeacherRegister(teacher: Teacher) {
    console.log(teacher);
  }
}
