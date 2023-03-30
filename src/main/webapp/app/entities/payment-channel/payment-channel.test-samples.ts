import dayjs from 'dayjs/esm';

import { IPaymentChannel, NewPaymentChannel } from './payment-channel.model';

export const sampleWithRequiredData: IPaymentChannel = {
  id: 20891,
  recordUniqueIdentifier: '4b610604-3cff-47a9-815a-1398e7e2a0c1',
  channelId: 76537,
  channelCode: 17653,
  channelName: 18204,
  status: false,
};

export const sampleWithPartialData: IPaymentChannel = {
  id: 54001,
  recordUniqueIdentifier: 'fd0e8c78-e11e-4a30-b4dc-1951e5375b84',
  channelId: 7411,
  channelCode: 99220,
  channelName: 56642,
  status: true,
  freeField1: 'Dynamic optimize',
  updatedAt: dayjs('2023-03-30'),
};

export const sampleWithFullData: IPaymentChannel = {
  id: 28012,
  recordUniqueIdentifier: 'c1b081d0-cc5e-4094-9328-a692f8028f3e',
  channelId: 45712,
  channelCode: 98366,
  channelName: 14824,
  status: false,
  freeField1: 'Planner',
  freeField2: 'fault-tolerant',
  freeField3: 'innovate',
  createdAt: dayjs('2023-03-30T09:32'),
  updatedAt: dayjs('2023-03-30'),
  isDeleted: false,
};

export const sampleWithNewData: NewPaymentChannel = {
  recordUniqueIdentifier: 'eba3dda6-f22d-46f4-a01b-636be9ae55be',
  channelId: 94450,
  channelCode: 93595,
  channelName: 22502,
  status: true,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
