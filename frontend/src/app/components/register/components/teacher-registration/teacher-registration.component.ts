import {Component, EventEmitter, Output} from '@angular/core';
import {Teacher} from '../../../../models/user.model';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-teacher-registration',
  templateUrl: './teacher-registration.component.html',
  styleUrls: ['../../register.component.scss']
})
export class TeacherRegistrationComponent {

  teacherRegisterForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
    name: ['', Validators.required],
    department: ['', Validators.required],
    room: ['', Validators.required],
  });

  @Output() onTeacherRegister = new EventEmitter<Teacher>();

  constructor(private formBuilder: FormBuilder) {
  }

  onSubmit() {
    if (this.teacherRegisterForm.valid) {
      this.onTeacherRegister.emit(this.teacherRegisterForm.value);
    }
  }
}

