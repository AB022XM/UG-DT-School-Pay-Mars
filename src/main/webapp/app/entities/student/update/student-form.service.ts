import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStudent, NewStudent } from '../student.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStudent for edit and NewStudentFormGroupInput for create.
 */
type StudentFormGroupInput = IStudent | PartialWithRequiredKeyOf<NewStudent>;

type StudentFormDefaults = Pick<NewStudent, 'id' | 'status'>;

type StudentFormGroupContent = {
  id: FormControl<IStudent['id'] | NewStudent['id']>;
  recordUniqueIdentifier: FormControl<IStudent['recordUniqueIdentifier']>;
  studentId: FormControl<IStudent['studentId']>;
  firstName: FormControl<IStudent['firstName']>;
  middleName: FormControl<IStudent['middleName']>;
  lastName: FormControl<IStudent['lastName']>;
  paymentCode: FormControl<IStudent['paymentCode']>;
  dateOfBirth: FormControl<IStudent['dateOfBirth']>;
  outStandingAmount: FormControl<IStudent['outStandingAmount']>;
  status: FormControl<IStudent['status']>;
  studentContact: FormControl<IStudent['studentContact']>;
  studentAddress: FormControl<IStudent['studentAddress']>;
  freeField1: FormControl<IStudent['freeField1']>;
  freeField2: FormControl<IStudent['freeField2']>;
  freeField3: FormControl<IStudent['freeField3']>;
  createdAt: FormControl<IStudent['createdAt']>;
  updatedAt: FormControl<IStudent['updatedAt']>;
  isDeleted: FormControl<IStudent['isDeleted']>;
  studentClass: FormControl<IStudent['studentClass']>;
  school: FormControl<IStudent['school']>;
};

export type StudentFormGroup = FormGroup<StudentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StudentFormService {
  createStudentFormGroup(student: StudentFormGroupInput = { id: null }): StudentFormGroup {
    const studentRawValue = {
      ...this.getFormDefaults(),
      ...student,
    };
    return new FormGroup<StudentFormGroupContent>({
      id: new FormControl(
        { value: studentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      recordUniqueIdentifier: new FormControl(studentRawValue.recordUniqueIdentifier, {
        validators: [Validators.required],
      }),
      studentId: new FormControl(studentRawValue.studentId, {
        validators: [Validators.required],
      }),
      firstName: new FormControl(studentRawValue.firstName, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(30)],
      }),
      middleName: new FormControl(studentRawValue.middleName, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(30)],
      }),
      lastName: new FormControl(studentRawValue.lastName, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(30)],
      }),
      paymentCode: new FormControl(studentRawValue.paymentCode, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(20)],
      }),
      dateOfBirth: new FormControl(studentRawValue.dateOfBirth, {
        validators: [Validators.required],
      }),
      outStandingAmount: new FormControl(studentRawValue.outStandingAmount, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(8)],
      }),
      status: new FormControl(studentRawValue.status, {
        validators: [Validators.required],
      }),
      studentContact: new FormControl(studentRawValue.studentContact),
      studentAddress: new FormControl(studentRawValue.studentAddress),
      freeField1: new FormControl(studentRawValue.freeField1),
      freeField2: new FormControl(studentRawValue.freeField2),
      freeField3: new FormControl(studentRawValue.freeField3),
      createdAt: new FormControl(studentRawValue.createdAt),
      updatedAt: new FormControl(studentRawValue.updatedAt),
      isDeleted: new FormControl(studentRawValue.isDeleted),
      studentClass: new FormControl(studentRawValue.studentClass),
      school: new FormControl(studentRawValue.school),
    });
  }

  getStudent(form: StudentFormGroup): IStudent | NewStudent {
    return form.getRawValue() as IStudent | NewStudent;
  }

  resetForm(form: StudentFormGroup, student: StudentFormGroupInput): void {
    const studentRawValue = { ...this.getFormDefaults(), ...student };
    form.reset(
      {
        ...studentRawValue,
        id: { value: studentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): StudentFormDefaults {
    return {
      id: null,
      status: false,
    };
  }
}
