import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { StudentClassComponent } from './list/student-class.component';
import { StudentClassDetailComponent } from './detail/student-class-detail.component';
import { StudentClassUpdateComponent } from './update/student-class-update.component';
import { StudentClassDeleteDialogComponent } from './delete/student-class-delete-dialog.component';
import { StudentClassRoutingModule } from './route/student-class-routing.module';

@NgModule({
  imports: [SharedModule, StudentClassRoutingModule],
  declarations: [StudentClassComponent, StudentClassDetailComponent, StudentClassUpdateComponent, StudentClassDeleteDialogComponent],
})
export class StudentClassModule {}
