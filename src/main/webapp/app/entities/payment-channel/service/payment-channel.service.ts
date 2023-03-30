import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPaymentChannel, NewPaymentChannel } from '../payment-channel.model';

export type PartialUpdatePaymentChannel = Partial<IPaymentChannel> & Pick<IPaymentChannel, 'id'>;

type RestOf<T extends IPaymentChannel | NewPaymentChannel> = Omit<T, 'createdAt' | 'updatedAt'> & {
  createdAt?: string | null;
  updatedAt?: string | null;
};

export type RestPaymentChannel = RestOf<IPaymentChannel>;

export type NewRestPaymentChannel = RestOf<NewPaymentChannel>;

export type PartialUpdateRestPaymentChannel = RestOf<PartialUpdatePaymentChannel>;

export type EntityResponseType = HttpResponse<IPaymentChannel>;
export type EntityArrayResponseType = HttpResponse<IPaymentChannel[]>;

@Injectable({ providedIn: 'root' })
export class PaymentChannelService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/payment-channels');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(paymentChannel: NewPaymentChannel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentChannel);
    return this.http
      .post<RestPaymentChannel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(paymentChannel: IPaymentChannel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentChannel);
    return this.http
      .put<RestPaymentChannel>(`${this.resourceUrl}/${this.getPaymentChannelIdentifier(paymentChannel)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(paymentChannel: PartialUpdatePaymentChannel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentChannel);
    return this.http
      .patch<RestPaymentChannel>(`${this.resourceUrl}/${this.getPaymentChannelIdentifier(paymentChannel)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestPaymentChannel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestPaymentChannel[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPaymentChannelIdentifier(paymentChannel: Pick<IPaymentChannel, 'id'>): number {
    return paymentChannel.id;
  }

  comparePaymentChannel(o1: Pick<IPaymentChannel, 'id'> | null, o2: Pick<IPaymentChannel, 'id'> | null): boolean {
    return o1 && o2 ? this.getPaymentChannelIdentifier(o1) === this.getPaymentChannelIdentifier(o2) : o1 === o2;
  }

  addPaymentChannelToCollectionIfMissing<Type extends Pick<IPaymentChannel, 'id'>>(
    paymentChannelCollection: Type[],
    ...paymentChannelsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const paymentChannels: Type[] = paymentChannelsToCheck.filter(isPresent);
    if (paymentChannels.length > 0) {
      const paymentChannelCollectionIdentifiers = paymentChannelCollection.map(
        paymentChannelItem => this.getPaymentChannelIdentifier(paymentChannelItem)!
      );
      const paymentChannelsToAdd = paymentChannels.filter(paymentChannelItem => {
        const paymentChannelIdentifier = this.getPaymentChannelIdentifier(paymentChannelItem);
        if (paymentChannelCollectionIdentifiers.includes(paymentChannelIdentifier)) {
          return false;
        }
        paymentChannelCollectionIdentifiers.push(paymentChannelIdentifier);
        return true;
      });
      return [...paymentChannelsToAdd, ...paymentChannelCollection];
    }
    return paymentChannelCollection;
  }

  protected convertDateFromClient<T extends IPaymentChannel | NewPaymentChannel | PartialUpdatePaymentChannel>(
    paymentChannel: T
  ): RestOf<T> {
    return {
      ...paymentChannel,
      createdAt: paymentChannel.createdAt?.toJSON() ?? null,
      updatedAt: paymentChannel.updatedAt?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restPaymentChannel: RestPaymentChannel): IPaymentChannel {
    return {
      ...restPaymentChannel,
      createdAt: restPaymentChannel.createdAt ? dayjs(restPaymentChannel.createdAt) : undefined,
      updatedAt: restPaymentChannel.updatedAt ? dayjs(restPaymentChannel.updatedAt) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestPaymentChannel>): HttpResponse<IPaymentChannel> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestPaymentChannel[]>): HttpResponse<IPaymentChannel[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
