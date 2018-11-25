import {Subject} from './subject.model';

export enum Role {
  STUDENT = 'ROLE_STUDENT',
  TEACHER = 'ROLE_TEACHER'
}

export interface User {
  id: number;
  username: string;
  role: Role;
  name: string;
  subjects: Subject[];
}

export interface Student extends User {
  department: string;
  room: string;
}

export interface Teacher extends User {
  yearStarted: number;
  semester: number;
}
