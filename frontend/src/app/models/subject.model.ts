export interface Subject {
  id: number;
  name: string;
  day: string;
  time: string;
  location: string;
}

export interface ExtendedSubject extends Subject{
  isTaken: boolean;
}
