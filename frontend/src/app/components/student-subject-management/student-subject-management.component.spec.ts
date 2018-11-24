import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentSubjectManagementComponent } from './student-subject-management.component';

describe('StudentSubjectManagementComponent', () => {
  let component: StudentSubjectManagementComponent;
  let fixture: ComponentFixture<StudentSubjectManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentSubjectManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentSubjectManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
