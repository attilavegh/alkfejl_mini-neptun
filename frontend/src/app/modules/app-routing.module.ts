import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';

import {AppComponent} from '../app.component';
import {TimetableComponent} from '../components/timetable/timetable.component';
import {SubjectManagementComponent} from '../components/subject-management/subject-management.component';
import {StudentGuard} from '../services/guards/student.guard';
import {SubjectDetailComponent} from '../components/subject-detail/subject-detail.component';
import {TeacherGuard} from '../services/guards/teacher.guard';

const routes: Routes = [
  {path: 'login', component: AppComponent},
  {path: '', redirectTo: 'subjects', pathMatch: 'full'},
  {path: 'subjects', component: SubjectManagementComponent},
  {path: 'subjects/new', component: SubjectDetailComponent, canActivate: [TeacherGuard]},
  {path: 'subjects/edit/:id', component: SubjectDetailComponent, canActivate: [TeacherGuard]},
  {path: 'timetable', component: TimetableComponent, canActivate: [StudentGuard]}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule {
}
