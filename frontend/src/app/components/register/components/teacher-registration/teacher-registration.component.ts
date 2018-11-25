import {Component, EventEmitter, Output} from '@angular/core';
import {Teacher} from '../../../../models/user.model';
import {FormBuilder, Validators} from '@angular/forms';
import {checkPasswords} from '../../register.component';

@Component({
  selector: 'app-teacher-registration',
  templateUrl: './teacher-registration.component.html',
  styleUrls: ['../../register.component.scss']
})
export class TeacherRegistrationComponent {

  teacherRegisterForm = this.formBuilder.group({
    username: [null, Validators.required],
    password: [null, Validators.required],
    passwordConfirm: [null, Validators.required],
    name: [null, Validators.required],
    department: [null, Validators.required],
    room: [null, Validators.required],
  },  {validators: checkPasswords});

  @Output() onTeacherRegister = new EventEmitter<Teacher>();

  constructor(private formBuilder: FormBuilder) {
  }

  onSubmit() {
    if (this.teacherRegisterForm.valid) {
      this.onTeacherRegister.emit(this.teacherRegisterForm.value);
    }
  }
}

