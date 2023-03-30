import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStudentClass, NewStudentClass } from '../student-class.model';

export type PartialUpdateStudentClass = Partial<IStudentClass> & Pick<IStudentClass, 'id'>;

type RestOf<T extends IStudentClass | NewStudentClass> = Omit<T, 'createdAt' | 'updatedAt' | 'isDeleted'> & {
  createdAt?: string | null;
  updatedAt?: string | null;
  isDeleted?: string | null;
};

export type RestStudentClass = RestOf<IStudentClass>;

export type NewRestStudentClass = RestOf<NewStudentClass>;

export type PartialUpdateRestStudentClass = RestOf<PartialUpdateStudentClass>;

export type EntityResponseType = HttpResponse<IStudentClass>;
export type EntityArrayResponseType = HttpResponse<IStudentClass[]>;

@Injectable({ providedIn: 'root' })
export class StudentClassService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/student-classes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(studentClass: NewStudentClass): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(studentClass);
    return this.http
      .post<RestStudentClass>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(studentClass: IStudentClass): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(studentClass);
    return this.http
      .put<RestStudentClass>(`${this.resourceUrl}/${this.getStudentClassIdentifier(studentClass)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(studentClass: PartialUpdateStudentClass): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(studentClass);
    return this.http
      .patch<RestStudentClass>(`${this.resourceUrl}/${this.getStudentClassIdentifier(studentClass)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestStudentClass>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestStudentClass[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getStudentClassIdentifier(studentClass: Pick<IStudentClass, 'id'>): number {
    return studentClass.id;
  }

  compareStudentClass(o1: Pick<IStudentClass, 'id'> | null, o2: Pick<IStudentClass, 'id'> | null): boolean {
    return o1 && o2 ? this.getStudentClassIdentifier(o1) === this.getStudentClassIdentifier(o2) : o1 === o2;
  }

  addStudentClassToCollectionIfMissing<Type extends Pick<IStudentClass, 'id'>>(
    studentClassCollection: Type[],
    ...studentClassesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const studentClasses: Type[] = studentClassesToCheck.filter(isPresent);
    if (studentClasses.length > 0) {
      const studentClassCollectionIdentifiers = studentClassCollection.map(
        studentClassItem => this.getStudentClassIdentifier(studentClassItem)!
      );
      const studentClassesToAdd = studentClasses.filter(studentClassItem => {
        const studentClassIdentifier = this.getStudentClassIdentifier(studentClassItem);
        if (studentClassCollectionIdentifiers.includes(studentClassIdentifier)) {
          return false;
        }
        studentClassCollectionIdentifiers.push(studentClassIdentifier);
        return true;
      });
      return [...studentClassesToAdd, ...studentClassCollection];
    }
    return studentClassCollection;
  }

  protected convertDateFromClient<T extends IStudentClass | NewStudentClass | PartialUpdateStudentClass>(studentClass: T): RestOf<T> {
    return {
      ...studentClass,
      createdAt: studentClass.createdAt?.format(DATE_FORMAT) ?? null,
      updatedAt: studentClass.updatedAt?.format(DATE_FORMAT) ?? null,
      isDeleted: studentClass.isDeleted?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restStudentClass: RestStudentClass): IStudentClass {
    return {
      ...restStudentClass,
      createdAt: restStudentClass.createdAt ? dayjs(restStudentClass.createdAt) : undefined,
      updatedAt: restStudentClass.updatedAt ? dayjs(restStudentClass.updatedAt) : undefined,
      isDeleted: restStudentClass.isDeleted ? dayjs(restStudentClass.isDeleted) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestStudentClass>): HttpResponse<IStudentClass> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestStudentClass[]>): HttpResponse<IStudentClass[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
