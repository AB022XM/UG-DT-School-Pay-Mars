import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IStudentClass } from '../student-class.model';
import { StudentClassService } from '../service/student-class.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './student-class-delete-dialog.component.html',
})
export class StudentClassDeleteDialogComponent {
  studentClass?: IStudentClass;

  constructor(protected studentClassService: StudentClassService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.studentClassService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
