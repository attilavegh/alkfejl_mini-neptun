import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './modules/app-routing.module';
import {HeaderComponent} from './components/header/header.component';
import {MaterialModule} from './modules/material.module';
import {TimetableComponent} from './components/timetable/timetable.component';
import {TokenInterceptorService} from './services/token-interceptor/token-interceptor.service';
import {StudentSubjectManagementComponent} from './components/subject-management/components/student-subject-management/student-subject-management.component';
import {TeacherSubjectManagementComponent} from './components/subject-management/components/teacher-subject-management/teacher-subject-management.component';
import {PageTitleComponent} from './components/page-title/page-title.component';
import {SubjectManagementComponent} from './components/subject-management/subject-management.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {StudentRegistrationComponent} from './components/register/components/student-registration/student-registration.component';
import {TeacherRegistrationComponent} from './components/register/components/teacher-registration/teacher-registration.component';
import {ErrorPanelComponent} from './components/error-panel/error-panel.component';
import {SubjectDetailComponent} from './components/subject-detail/subject-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TimetableComponent,
    StudentSubjectManagementComponent,
    TeacherSubjectManagementComponent,
    PageTitleComponent,
    SubjectManagementComponent,
    SubjectDetailComponent,
    LoginComponent,
    RegisterComponent,
    StudentRegistrationComponent,
    TeacherRegistrationComponent,
    ErrorPanelComponent
  ],
  imports: [
    AppRoutingModule,
    RouterModule,
    BrowserModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
