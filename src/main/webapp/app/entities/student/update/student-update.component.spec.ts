import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { StudentFormService } from './student-form.service';
import { StudentService } from '../service/student.service';
import { IStudent } from '../student.model';
import { IStudentClass } from 'app/entities/student-class/student-class.model';
import { StudentClassService } from 'app/entities/student-class/service/student-class.service';
import { ISchool } from 'app/entities/school/school.model';
import { SchoolService } from 'app/entities/school/service/school.service';

import { StudentUpdateComponent } from './student-update.component';

describe('Student Management Update Component', () => {
  let comp: StudentUpdateComponent;
  let fixture: ComponentFixture<StudentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let studentFormService: StudentFormService;
  let studentService: StudentService;
  let studentClassService: StudentClassService;
  let schoolService: SchoolService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [StudentUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(StudentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StudentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    studentFormService = TestBed.inject(StudentFormService);
    studentService = TestBed.inject(StudentService);
    studentClassService = TestBed.inject(StudentClassService);
    schoolService = TestBed.inject(SchoolService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call StudentClass query and add missing value', () => {
      const student: IStudent = { id: 456 };
      const studentClass: IStudentClass = { id: 96750 };
      student.studentClass = studentClass;

      const studentClassCollection: IStudentClass[] = [{ id: 74217 }];
      jest.spyOn(studentClassService, 'query').mockReturnValue(of(new HttpResponse({ body: studentClassCollection })));
      const additionalStudentClasses = [studentClass];
      const expectedCollection: IStudentClass[] = [...additionalStudentClasses, ...studentClassCollection];
      jest.spyOn(studentClassService, 'addStudentClassToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ student });
      comp.ngOnInit();

      expect(studentClassService.query).toHaveBeenCalled();
      expect(studentClassService.addStudentClassToCollectionIfMissing).toHaveBeenCalledWith(
        studentClassCollection,
        ...additionalStudentClasses.map(expect.objectContaining)
      );
      expect(comp.studentClassesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call School query and add missing value', () => {
      const student: IStudent = { id: 456 };
      const school: ISchool = { id: 30511 };
      student.school = school;

      const schoolCollection: ISchool[] = [{ id: 96366 }];
      jest.spyOn(schoolService, 'query').mockReturnValue(of(new HttpResponse({ body: schoolCollection })));
      const additionalSchools = [school];
      const expectedCollection: ISchool[] = [...additionalSchools, ...schoolCollection];
      jest.spyOn(schoolService, 'addSchoolToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ student });
      comp.ngOnInit();

      expect(schoolService.query).toHaveBeenCalled();
      expect(schoolService.addSchoolToCollectionIfMissing).toHaveBeenCalledWith(
        schoolCollection,
        ...additionalSchools.map(expect.objectContaining)
      );
      expect(comp.schoolsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const student: IStudent = { id: 456 };
      const studentClass: IStudentClass = { id: 50945 };
      student.studentClass = studentClass;
      const school: ISchool = { id: 46561 };
      student.school = school;

      activatedRoute.data = of({ student });
      comp.ngOnInit();

      expect(comp.studentClassesSharedCollection).toContain(studentClass);
      expect(comp.schoolsSharedCollection).toContain(school);
      expect(comp.student).toEqual(student);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStudent>>();
      const student = { id: 123 };
      jest.spyOn(studentFormService, 'getStudent').mockReturnValue(student);
      jest.spyOn(studentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ student });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: student }));
      saveSubject.complete();

      // THEN
      expect(studentFormService.getStudent).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(studentService.update).toHaveBeenCalledWith(expect.objectContaining(student));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStudent>>();
      const student = { id: 123 };
      jest.spyOn(studentFormService, 'getStudent').mockReturnValue({ id: null });
      jest.spyOn(studentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ student: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: student }));
      saveSubject.complete();

      // THEN
      expect(studentFormService.getStudent).toHaveBeenCalled();
      expect(studentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStudent>>();
      const student = { id: 123 };
      jest.spyOn(studentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ student });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(studentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareStudentClass', () => {
      it('Should forward to studentClassService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(studentClassService, 'compareStudentClass');
        comp.compareStudentClass(entity, entity2);
        expect(studentClassService.compareStudentClass).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareSchool', () => {
      it('Should forward to schoolService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(schoolService, 'compareSchool');
        comp.compareSchool(entity, entity2);
        expect(schoolService.compareSchool).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
