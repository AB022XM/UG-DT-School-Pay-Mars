import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { StudentFormService, StudentFormGroup } from './student-form.service';
import { IStudent } from '../student.model';
import { StudentService } from '../service/student.service';
import { IStudentClass } from 'app/entities/student-class/student-class.model';
import { StudentClassService } from 'app/entities/student-class/service/student-class.service';
import { ISchool } from 'app/entities/school/school.model';
import { SchoolService } from 'app/entities/school/service/school.service';

@Component({
  selector: 'jhi-student-update',
  templateUrl: './student-update.component.html',
})
export class StudentUpdateComponent implements OnInit {
  isSaving = false;
  student: IStudent | null = null;

  studentClassesSharedCollection: IStudentClass[] = [];
  schoolsSharedCollection: ISchool[] = [];

  editForm: StudentFormGroup = this.studentFormService.createStudentFormGroup();

  constructor(
    protected studentService: StudentService,
    protected studentFormService: StudentFormService,
    protected studentClassService: StudentClassService,
    protected schoolService: SchoolService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareStudentClass = (o1: IStudentClass | null, o2: IStudentClass | null): boolean =>
    this.studentClassService.compareStudentClass(o1, o2);

  compareSchool = (o1: ISchool | null, o2: ISchool | null): boolean => this.schoolService.compareSchool(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ student }) => {
      this.student = student;
      if (student) {
        this.updateForm(student);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const student = this.studentFormService.getStudent(this.editForm);
    if (student.id !== null) {
      this.subscribeToSaveResponse(this.studentService.update(student));
    } else {
      this.subscribeToSaveResponse(this.studentService.create(student));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStudent>>): void {
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

  protected updateForm(student: IStudent): void {
    this.student = student;
    this.studentFormService.resetForm(this.editForm, student);

    this.studentClassesSharedCollection = this.studentClassService.addStudentClassToCollectionIfMissing<IStudentClass>(
      this.studentClassesSharedCollection,
      student.studentClass
    );
    this.schoolsSharedCollection = this.schoolService.addSchoolToCollectionIfMissing<ISchool>(this.schoolsSharedCollection, student.school);
  }

  protected loadRelationshipsOptions(): void {
    this.studentClassService
      .query()
      .pipe(map((res: HttpResponse<IStudentClass[]>) => res.body ?? []))
      .pipe(
        map((studentClasses: IStudentClass[]) =>
          this.studentClassService.addStudentClassToCollectionIfMissing<IStudentClass>(studentClasses, this.student?.studentClass)
        )
      )
      .subscribe((studentClasses: IStudentClass[]) => (this.studentClassesSharedCollection = studentClasses));

    this.schoolService
      .query()
      .pipe(map((res: HttpResponse<ISchool[]>) => res.body ?? []))
      .pipe(map((schools: ISchool[]) => this.schoolService.addSchoolToCollectionIfMissing<ISchool>(schools, this.student?.school)))
      .subscribe((schools: ISchool[]) => (this.schoolsSharedCollection = schools));
  }
}
