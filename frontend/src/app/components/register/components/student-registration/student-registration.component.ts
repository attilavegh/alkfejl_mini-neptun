import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Student} from '../../../../models/user.model';

@Component({
  selector: 'app-student-registration',
  templateUrl: './student-registration.component.html',
  styleUrls: ['../../register.component.scss']
})
export class StudentRegistrationComponent {

  studentRegisterForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
    name: ['', Validators.required],
  });

  @Output() onStudentRegister = new EventEmitter<Student>();
  @Output() onMatchingPasswords = new EventEmitter<boolean>();

  constructor(private formBuilder: FormBuilder) {
  }

  onSubmit() {
    if (this.studentRegisterForm.get('password').invalid) {
      this.onMatchingPasswords.emit();
    }

    if (this.studentRegisterForm.valid) {
      this.onStudentRegister.emit(this.studentRegisterForm.value);
    }
  }
}
