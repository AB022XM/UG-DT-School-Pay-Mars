import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { StudentClassFormService } from './student-class-form.service';
import { StudentClassService } from '../service/student-class.service';
import { IStudentClass } from '../student-class.model';

import { StudentClassUpdateComponent } from './student-class-update.component';

describe('StudentClass Management Update Component', () => {
  let comp: StudentClassUpdateComponent;
  let fixture: ComponentFixture<StudentClassUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let studentClassFormService: StudentClassFormService;
  let studentClassService: StudentClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [StudentClassUpdateComponent],
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
      .overrideTemplate(StudentClassUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StudentClassUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    studentClassFormService = TestBed.inject(StudentClassFormService);
    studentClassService = TestBed.inject(StudentClassService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const studentClass: IStudentClass = { id: 456 };

      activatedRoute.data = of({ studentClass });
      comp.ngOnInit();

      expect(comp.studentClass).toEqual(studentClass);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStudentClass>>();
      const studentClass = { id: 123 };
      jest.spyOn(studentClassFormService, 'getStudentClass').mockReturnValue(studentClass);
      jest.spyOn(studentClassService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ studentClass });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: studentClass }));
      saveSubject.complete();

      // THEN
      expect(studentClassFormService.getStudentClass).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(studentClassService.update).toHaveBeenCalledWith(expect.objectContaining(studentClass));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStudentClass>>();
      const studentClass = { id: 123 };
      jest.spyOn(studentClassFormService, 'getStudentClass').mockReturnValue({ id: null });
      jest.spyOn(studentClassService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ studentClass: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: studentClass }));
      saveSubject.complete();

      // THEN
      expect(studentClassFormService.getStudentClass).toHaveBeenCalled();
      expect(studentClassService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStudentClass>>();
      const studentClass = { id: 123 };
      jest.spyOn(studentClassService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ studentClass });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(studentClassService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
