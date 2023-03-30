import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IContactInfo, NewContactInfo } from '../contact-info.model';

export type PartialUpdateContactInfo = Partial<IContactInfo> & Pick<IContactInfo, 'id'>;

type RestOf<T extends IContactInfo | NewContactInfo> = Omit<T, 'createdAt' | 'updatedAt' | 'isDeleted'> & {
  createdAt?: string | null;
  updatedAt?: string | null;
  isDeleted?: string | null;
};

export type RestContactInfo = RestOf<IContactInfo>;

export type NewRestContactInfo = RestOf<NewContactInfo>;

export type PartialUpdateRestContactInfo = RestOf<PartialUpdateContactInfo>;

export type EntityResponseType = HttpResponse<IContactInfo>;
export type EntityArrayResponseType = HttpResponse<IContactInfo[]>;

@Injectable({ providedIn: 'root' })
export class ContactInfoService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/contact-infos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(contactInfo: NewContactInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contactInfo);
    return this.http
      .post<RestContactInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(contactInfo: IContactInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contactInfo);
    return this.http
      .put<RestContactInfo>(`${this.resourceUrl}/${this.getContactInfoIdentifier(contactInfo)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(contactInfo: PartialUpdateContactInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contactInfo);
    return this.http
      .patch<RestContactInfo>(`${this.resourceUrl}/${this.getContactInfoIdentifier(contactInfo)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestContactInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestContactInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getContactInfoIdentifier(contactInfo: Pick<IContactInfo, 'id'>): number {
    return contactInfo.id;
  }

  compareContactInfo(o1: Pick<IContactInfo, 'id'> | null, o2: Pick<IContactInfo, 'id'> | null): boolean {
    return o1 && o2 ? this.getContactInfoIdentifier(o1) === this.getContactInfoIdentifier(o2) : o1 === o2;
  }

  addContactInfoToCollectionIfMissing<Type extends Pick<IContactInfo, 'id'>>(
    contactInfoCollection: Type[],
    ...contactInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const contactInfos: Type[] = contactInfosToCheck.filter(isPresent);
    if (contactInfos.length > 0) {
      const contactInfoCollectionIdentifiers = contactInfoCollection.map(
        contactInfoItem => this.getContactInfoIdentifier(contactInfoItem)!
      );
      const contactInfosToAdd = contactInfos.filter(contactInfoItem => {
        const contactInfoIdentifier = this.getContactInfoIdentifier(contactInfoItem);
        if (contactInfoCollectionIdentifiers.includes(contactInfoIdentifier)) {
          return false;
        }
        contactInfoCollectionIdentifiers.push(contactInfoIdentifier);
        return true;
      });
      return [...contactInfosToAdd, ...contactInfoCollection];
    }
    return contactInfoCollection;
  }

  protected convertDateFromClient<T extends IContactInfo | NewContactInfo | PartialUpdateContactInfo>(contactInfo: T): RestOf<T> {
    return {
      ...contactInfo,
      createdAt: contactInfo.createdAt?.format(DATE_FORMAT) ?? null,
      updatedAt: contactInfo.updatedAt?.format(DATE_FORMAT) ?? null,
      isDeleted: contactInfo.isDeleted?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restContactInfo: RestContactInfo): IContactInfo {
    return {
      ...restContactInfo,
      createdAt: restContactInfo.createdAt ? dayjs(restContactInfo.createdAt) : undefined,
      updatedAt: restContactInfo.updatedAt ? dayjs(restContactInfo.updatedAt) : undefined,
      isDeleted: restContactInfo.isDeleted ? dayjs(restContactInfo.isDeleted) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestContactInfo>): HttpResponse<IContactInfo> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestContactInfo[]>): HttpResponse<IContactInfo[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
