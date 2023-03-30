import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { AssociatedFeesFormService, AssociatedFeesFormGroup } from './associated-fees-form.service';
import { IAssociatedFees } from '../associated-fees.model';
import { AssociatedFeesService } from '../service/associated-fees.service';
import { IStudentClass } from 'app/entities/student-class/student-class.model';
import { StudentClassService } from 'app/entities/student-class/service/student-class.service';

@Component({
  selector: 'jhi-associated-fees-update',
  templateUrl: './associated-fees-update.component.html',
})
export class AssociatedFeesUpdateComponent implements OnInit {
  isSaving = false;
  associatedFees: IAssociatedFees | null = null;

  studentClassesSharedCollection: IStudentClass[] = [];

  editForm: AssociatedFeesFormGroup = this.associatedFeesFormService.createAssociatedFeesFormGroup();

  constructor(
    protected associatedFeesService: AssociatedFeesService,
    protected associatedFeesFormService: AssociatedFeesFormService,
    protected studentClassService: StudentClassService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareStudentClass = (o1: IStudentClass | null, o2: IStudentClass | null): boolean =>
    this.studentClassService.compareStudentClass(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ associatedFees }) => {
      this.associatedFees = associatedFees;
      if (associatedFees) {
        this.updateForm(associatedFees);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const associatedFees = this.associatedFeesFormService.getAssociatedFees(this.editForm);
    if (associatedFees.id !== null) {
      this.subscribeToSaveResponse(this.associatedFeesService.update(associatedFees));
    } else {
      this.subscribeToSaveResponse(this.associatedFeesService.create(associatedFees));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssociatedFees>>): void {
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

  protected updateForm(associatedFees: IAssociatedFees): void {
    this.associatedFees = associatedFees;
    this.associatedFeesFormService.resetForm(this.editForm, associatedFees);

    this.studentClassesSharedCollection = this.studentClassService.addStudentClassToCollectionIfMissing<IStudentClass>(
      this.studentClassesSharedCollection,
      associatedFees.studentClass
    );
  }

  protected loadRelationshipsOptions(): void {
    this.studentClassService
      .query()
      .pipe(map((res: HttpResponse<IStudentClass[]>) => res.body ?? []))
      .pipe(
        map((studentClasses: IStudentClass[]) =>
          this.studentClassService.addStudentClassToCollectionIfMissing<IStudentClass>(studentClasses, this.associatedFees?.studentClass)
        )
      )
      .subscribe((studentClasses: IStudentClass[]) => (this.studentClassesSharedCollection = studentClasses));
  }
}
