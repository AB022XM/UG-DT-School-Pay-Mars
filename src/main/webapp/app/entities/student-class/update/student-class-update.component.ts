import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { StudentClassFormService, StudentClassFormGroup } from './student-class-form.service';
import { IStudentClass } from '../student-class.model';
import { StudentClassService } from '../service/student-class.service';

@Component({
  selector: 'jhi-student-class-update',
  templateUrl: './student-class-update.component.html',
})
export class StudentClassUpdateComponent implements OnInit {
  isSaving = false;
  studentClass: IStudentClass | null = null;

  editForm: StudentClassFormGroup = this.studentClassFormService.createStudentClassFormGroup();

  constructor(
    protected studentClassService: StudentClassService,
    protected studentClassFormService: StudentClassFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ studentClass }) => {
      this.studentClass = studentClass;
      if (studentClass) {
        this.updateForm(studentClass);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const studentClass = this.studentClassFormService.getStudentClass(this.editForm);
    if (studentClass.id !== null) {
      this.subscribeToSaveResponse(this.studentClassService.update(studentClass));
    } else {
      this.subscribeToSaveResponse(this.studentClassService.create(studentClass));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStudentClass>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(studentClass: IStudentClass): void {
    this.studentClass = studentClass;
    this.studentClassFormService.resetForm(this.editForm, studentClass);
  }
}
