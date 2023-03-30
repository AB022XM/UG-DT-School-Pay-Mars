import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStudentClass, NewStudentClass } from '../student-class.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStudentClass for edit and NewStudentClassFormGroupInput for create.
 */
type StudentClassFormGroupInput = IStudentClass | PartialWithRequiredKeyOf<NewStudentClass>;

type StudentClassFormDefaults = Pick<NewStudentClass, 'id' | 'status'>;

type StudentClassFormGroupContent = {
  id: FormControl<IStudentClass['id'] | NewStudentClass['id']>;
  recordUniqueIdentifier: FormControl<IStudentClass['recordUniqueIdentifier']>;
  studentClassId: FormControl<IStudentClass['studentClassId']>;
  studentClassCode: FormControl<IStudentClass['studentClassCode']>;
  studentClassName: FormControl<IStudentClass['studentClassName']>;
  studentClassDescription: FormControl<IStudentClass['studentClassDescription']>;
  status: FormControl<IStudentClass['status']>;
  freeField1: FormControl<IStudentClass['freeField1']>;
  freeField2: FormControl<IStudentClass['freeField2']>;
  freeField3: FormControl<IStudentClass['freeField3']>;
  createdAt: FormControl<IStudentClass['createdAt']>;
  updatedAt: FormControl<IStudentClass['updatedAt']>;
  isDeleted: FormControl<IStudentClass['isDeleted']>;
};

export type StudentClassFormGroup = FormGroup<StudentClassFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StudentClassFormService {
  createStudentClassFormGroup(studentClass: StudentClassFormGroupInput = { id: null }): StudentClassFormGroup {
    const studentClassRawValue = {
      ...this.getFormDefaults(),
      ...studentClass,
    };
    return new FormGroup<StudentClassFormGroupContent>({
      id: new FormControl(
        { value: studentClassRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      recordUniqueIdentifier: new FormControl(studentClassRawValue.recordUniqueIdentifier, {
        validators: [Validators.required],
      }),
      studentClassId: new FormControl(studentClassRawValue.studentClassId, {
        validators: [Validators.required],
      }),
      studentClassCode: new FormControl(studentClassRawValue.studentClassCode, {
        validators: [Validators.required],
      }),
      studentClassName: new FormControl(studentClassRawValue.studentClassName, {
        validators: [Validators.required],
      }),
      studentClassDescription: new FormControl(studentClassRawValue.studentClassDescription, {
        validators: [Validators.required],
      }),
      status: new FormControl(studentClassRawValue.status, {
        validators: [Validators.required],
      }),
      freeField1: new FormControl(studentClassRawValue.freeField1),
      freeField2: new FormControl(studentClassRawValue.freeField2),
      freeField3: new FormControl(studentClassRawValue.freeField3),
      createdAt: new FormControl(studentClassRawValue.createdAt),
      updatedAt: new FormControl(studentClassRawValue.updatedAt),
      isDeleted: new FormControl(studentClassRawValue.isDeleted),
    });
  }

  getStudentClass(form: StudentClassFormGroup): IStudentClass | NewStudentClass {
    return form.getRawValue() as IStudentClass | NewStudentClass;
  }

  resetForm(form: StudentClassFormGroup, studentClass: StudentClassFormGroupInput): void {
    const studentClassRawValue = { ...this.getFormDefaults(), ...studentClass };
    form.reset(
      {
        ...studentClassRawValue,
        id: { value: studentClassRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): StudentClassFormDefaults {
    return {
      id: null,
      status: false,
    };
  }
}
