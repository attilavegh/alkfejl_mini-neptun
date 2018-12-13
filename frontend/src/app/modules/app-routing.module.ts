import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';

import {TimetableComponent} from '../components/timetable/timetable.component';
import {SubjectManagementComponent} from '../components/subject-management/subject-management.component';
import {SubjectDetailComponent} from '../components/subject-management/components/subject-detail/subject-detail.component';
import {AuthorizationGuard} from '../services/guards/authorization.guard';
import {LoginComponent} from '../components/login/login.component';
import {RegisterComponent} from '../components/register/register.component';
import {UserDetailsComponent} from '../components/user-details/user-details.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', canActivateChild: [AuthorizationGuard], children: [
      {path: '', redirectTo: 'subjects', pathMatch: 'full'},
      {path: 'user', component: UserDetailsComponent},
      {path: 'subjects', component: SubjectManagementComponent},
      {path: 'subjects/new', component: SubjectDetailComponent},
      {path: 'subjects/edit/:id', component: SubjectDetailComponent},
      {path: 'timetable', component: TimetableComponent}
    ]
  },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule {
}
