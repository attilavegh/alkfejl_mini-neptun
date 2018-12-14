import {Subject} from './subject.model';

export enum Role {
  STUDENT = 'ROLE_STUDENT',
  TEACHER = 'ROLE_TEACHER'
}

export interface NewStudent {
  username: string;
  password: string;
  name: string;
}

export interface NewTeacher {
  username: string;
  password: string;
  name: string;
  department: string;
  room: string;
}

export interface User {
  id: number;
  username: string;
  role: Role;
  name: string;
  subjects: Subject[];
}

export interface Teacher extends User {
  department: string;
  room: string;
}

export interface Student extends User {
  yearStarted: number;
  semester: number;
}
