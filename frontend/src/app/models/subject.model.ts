import {Teacher} from './user.model';

export interface Subject {
  id: number;
  name: string;
  day: string;
  time: string;
  location: string;
  teacher: Teacher;
}

export interface ExtendedSubject extends Subject{
  isTaken: boolean;
}
