import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../student-class.test-samples';

import { StudentClassFormService } from './student-class-form.service';

describe('StudentClass Form Service', () => {
  let service: StudentClassFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentClassFormService);
  });

  describe('Service methods', () => {
    describe('createStudentClassFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createStudentClassFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            recordUniqueIdentifier: expect.any(Object),
            studentClassId: expect.any(Object),
            studentClassCode: expect.any(Object),
            studentClassName: expect.any(Object),
            studentClassDescription: expect.any(Object),
            status: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
            freeField3: expect.any(Object),
            createdAt: expect.any(Object),
            updatedAt: expect.any(Object),
            isDeleted: expect.any(Object),
          })
        );
      });

      it('passing IStudentClass should create a new form with FormGroup', () => {
        const formGroup = service.createStudentClassFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            recordUniqueIdentifier: expect.any(Object),
            studentClassId: expect.any(Object),
            studentClassCode: expect.any(Object),
            studentClassName: expect.any(Object),
            studentClassDescription: expect.any(Object),
            status: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
            freeField3: expect.any(Object),
            createdAt: expect.any(Object),
            updatedAt: expect.any(Object),
            isDeleted: expect.any(Object),
          })
        );
      });
    });

    describe('getStudentClass', () => {
      it('should return NewStudentClass for default StudentClass initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createStudentClassFormGroup(sampleWithNewData);

        const studentClass = service.getStudentClass(formGroup) as any;

        expect(studentClass).toMatchObject(sampleWithNewData);
      });

      it('should return NewStudentClass for empty StudentClass initial value', () => {
        const formGroup = service.createStudentClassFormGroup();

        const studentClass = service.getStudentClass(formGroup) as any;

        expect(studentClass).toMatchObject({});
      });

      it('should return IStudentClass', () => {
        const formGroup = service.createStudentClassFormGroup(sampleWithRequiredData);

        const studentClass = service.getStudentClass(formGroup) as any;

        expect(studentClass).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IStudentClass should not enable id FormControl', () => {
        const formGroup = service.createStudentClassFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewStudentClass should disable id FormControl', () => {
        const formGroup = service.createStudentClassFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
