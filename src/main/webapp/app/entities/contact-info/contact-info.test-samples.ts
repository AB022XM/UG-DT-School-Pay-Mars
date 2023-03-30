import dayjs from 'dayjs/esm';

import { IContactInfo, NewContactInfo } from './contact-info.model';

export const sampleWithRequiredData: IContactInfo = {
  id: 8174,
  recordUniqueIdentifier: '6f8e4048-fa52-48e3-8f48-ad9b844c0763',
};

export const sampleWithPartialData: IContactInfo = {
  id: 73957,
  recordUniqueIdentifier: '3e78f841-5b06-4003-8e05-92642c60f8c5',
  contactId: 'overriding Proactive',
  emailAddress: 'Industrial Global',
  updatedAt: dayjs('2023-03-30'),
};

export const sampleWithFullData: IContactInfo = {
  id: 10983,
  recordUniqueIdentifier: 'a21d0e90-5d3a-4cd4-a2bb-de18f528591e',
  contactId: 'Infrastructure Texas',
  phoneNumber: 'Networked withdrawal',
  emailAddress: 'Web forecast',
  parentsPhoneNumber: 'Clothing Fresh',
  createdAt: dayjs('2023-03-30'),
  updatedAt: dayjs('2023-03-30'),
  isDeleted: dayjs('2023-03-30'),
};

export const sampleWithNewData: NewContactInfo = {
  recordUniqueIdentifier: '030f56d2-de93-44ef-ab0e-356583ad959b',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
