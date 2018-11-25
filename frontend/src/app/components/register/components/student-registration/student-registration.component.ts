import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Student} from '../../../../models/user.model';
import {checkPasswords} from '../../register.component';

@Component({
  selector: 'app-student-registration',
  templateUrl: './student-registration.component.html',
  styleUrls: ['../../register.component.scss']
})
export class StudentRegistrationComponent {

  studentRegisterForm = this.formBuilder.group({
    username: [null, Validators.required],
    password: [null, Validators.required],
    passwordConfirm: [null, Validators.required],
    name: [null, Validators.required],
  }, {validators: checkPasswords});

  @Output() onStudentRegister = new EventEmitter<Student>();

  constructor(private formBuilder: FormBuilder) {
  }

  onSubmit() {
    if (this.studentRegisterForm.valid) {
      this.onStudentRegister.emit(this.studentRegisterForm.value);
    }
  }
}
