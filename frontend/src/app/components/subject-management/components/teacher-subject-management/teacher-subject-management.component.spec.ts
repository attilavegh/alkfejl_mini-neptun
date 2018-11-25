import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherSubjectManagementComponent } from './teacher-subject-management.component';

describe('TeacherSubjectManagementComponent', () => {
  let component: TeacherSubjectManagementComponent;
  let fixture: ComponentFixture<TeacherSubjectManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherSubjectManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherSubjectManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
