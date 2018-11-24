import {Subject} from './subject.model';

export enum Role {
  STUDENT = 'STUDENT',
  TEACHER = 'TEACHER'
}


export interface User {
  id: number;
  username: string;
  role: Role;
  name: string;
  yearStarted: number;
  semester: number;
  subjects: Subject[];
}
