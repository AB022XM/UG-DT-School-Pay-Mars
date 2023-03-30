import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { StudentClassService } from '../service/student-class.service';

import { StudentClassComponent } from './student-class.component';

describe('StudentClass Management Component', () => {
  let comp: StudentClassComponent;
  let fixture: ComponentFixture<StudentClassComponent>;
  let service: StudentClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'student-class', component: StudentClassComponent }]), HttpClientTestingModule],
      declarations: [StudentClassComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(StudentClassComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StudentClassComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(StudentClassService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.studentClasses?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to studentClassService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getStudentClassIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getStudentClassIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
