import { Injectable } from '@angular/core';
import {MatDialog} from '@angular/material';

import {Observable} from 'rxjs';
import {filter, take} from 'rxjs/operators';

import {ConfirmationDialogComponent, ConfirmationDialogData} from '../../components/confirmation-dialog/confirmation-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class ConfirmationDialogService {

  constructor(public dialog: MatDialog) {
  }

  open(data: ConfirmationDialogData): Observable<any> {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '50%',
      data: data
    });

    return dialogRef.afterClosed().pipe(
      filter(result => !!result),
      take(1)
    );
  }
}
