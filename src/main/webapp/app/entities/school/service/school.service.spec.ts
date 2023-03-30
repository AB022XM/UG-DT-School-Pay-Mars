import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISchool } from '../school.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../school.test-samples';

import { SchoolService, RestSchool } from './school.service';

const requireRestSample: RestSchool = {
  ...sampleWithRequiredData,
  createdAt: sampleWithRequiredData.createdAt?.format(DATE_FORMAT),
  updatedAt: sampleWithRequiredData.updatedAt?.format(DATE_FORMAT),
  isDeleted: sampleWithRequiredData.isDeleted?.format(DATE_FORMAT),
};

describe('School Service', () => {
  let service: SchoolService;
  let httpMock: HttpTestingController;
  let expectedResult: ISchool | ISchool[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SchoolService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a School', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const school = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(school).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a School', () => {
      const school = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(school).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a School', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of School', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a School', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSchoolToCollectionIfMissing', () => {
      it('should add a School to an empty array', () => {
        const school: ISchool = sampleWithRequiredData;
        expectedResult = service.addSchoolToCollectionIfMissing([], school);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(school);
      });

      it('should not add a School to an array that contains it', () => {
        const school: ISchool = sampleWithRequiredData;
        const schoolCollection: ISchool[] = [
          {
            ...school,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSchoolToCollectionIfMissing(schoolCollection, school);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a School to an array that doesn't contain it", () => {
        const school: ISchool = sampleWithRequiredData;
        const schoolCollection: ISchool[] = [sampleWithPartialData];
        expectedResult = service.addSchoolToCollectionIfMissing(schoolCollection, school);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(school);
      });

      it('should add only unique School to an array', () => {
        const schoolArray: ISchool[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const schoolCollection: ISchool[] = [sampleWithRequiredData];
        expectedResult = service.addSchoolToCollectionIfMissing(schoolCollection, ...schoolArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const school: ISchool = sampleWithRequiredData;
        const school2: ISchool = sampleWithPartialData;
        expectedResult = service.addSchoolToCollectionIfMissing([], school, school2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(school);
        expect(expectedResult).toContain(school2);
      });

      it('should accept null and undefined values', () => {
        const school: ISchool = sampleWithRequiredData;
        expectedResult = service.addSchoolToCollectionIfMissing([], null, school, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(school);
      });

      it('should return initial array if no School is added', () => {
        const schoolCollection: ISchool[] = [sampleWithRequiredData];
        expectedResult = service.addSchoolToCollectionIfMissing(schoolCollection, undefined, null);
        expect(expectedResult).toEqual(schoolCollection);
      });
    });

    describe('compareSchool', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSchool(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareSchool(entity1, entity2);
        const compareResult2 = service.compareSchool(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareSchool(entity1, entity2);
        const compareResult2 = service.compareSchool(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareSchool(entity1, entity2);
        const compareResult2 = service.compareSchool(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
