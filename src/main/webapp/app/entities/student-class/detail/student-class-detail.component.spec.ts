import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StudentClassDetailComponent } from './student-class-detail.component';

describe('StudentClass Management Detail Component', () => {
  let comp: StudentClassDetailComponent;
  let fixture: ComponentFixture<StudentClassDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentClassDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ studentClass: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(StudentClassDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(StudentClassDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load studentClass on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.studentClass).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
