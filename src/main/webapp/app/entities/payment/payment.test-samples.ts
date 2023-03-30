import dayjs from 'dayjs/esm';

import { PaymentChannel } from 'app/entities/enumerations/payment-channel.model';

import { IPayment, NewPayment } from './payment.model';

export const sampleWithRequiredData: IPayment = {
  id: 47537,
  recordUniqueIdentifier: 'bb75e764-58a7-4ee9-bc22-72eb2852f49a',
  returnMessage: 'boliviano Loan Account',
  feeId: 'turquoise',
  firstName: 'Wilton',
  lastName: "O'Conner",
  middleName: 'Lek Brook',
  paymentCode: 'Corporate connect reintermediate',
  schoolCode: 'Tasty',
  schoolName: 'hack',
  studentClass: 'Sleek',
  paymentChannel: PaymentChannel['OVERTHECOUNTER'],
};

export const sampleWithPartialData: IPayment = {
  id: 87339,
  recordUniqueIdentifier: 'bbb21e50-aa77-48d3-9e6b-03e265fa7a5b',
  returnMessage: 'Group',
  feeAmount: 86218,
  feeDescription: 'plum Indiana',
  feeDueToDate: dayjs('2023-03-30'),
  feeId: 'clicks-and-mortar',
  firstName: 'Wendell',
  lastName: 'Hahn',
  middleName: 'Money',
  paymentCode: 'markets',
  schoolCode: 'whiteboard International',
  schoolName: 'Cameroon RSS',
  studentClass: 'Unbranded',
  paymentChannel: PaymentChannel['CHATBOT'],
  freeField1: 'Table Bacon',
  freeField2: 'Salad',
  freeField3: 'innovate array Plaza',
  createdAt: dayjs('2023-03-29'),
};

export const sampleWithFullData: IPayment = {
  id: 85347,
  recordUniqueIdentifier: 'd7007938-af69-4303-928d-8dca22bc4177',
  returnCode: 'str',
  returnMessage: 'payment connect users',
  processTimestamp: dayjs('2023-03-29'),
  feeAmount: 83115,
  feeDescription: 'payment ADP',
  feeDueFromDate: dayjs('2023-03-29'),
  feeDueToDate: dayjs('2023-03-30'),
  feeId: 'Fantastic Shilling',
  dateOfBirth: dayjs('2023-03-29'),
  firstName: 'Leila',
  lastName: 'Grimes',
  middleName: 'strategy end-to-end',
  outstandingAmount: 28821,
  paymentCode: 'SDR',
  schoolCode: 'Manager Divide Branding',
  schoolName: 'Health',
  studentClass: 'Tasty SAS',
  paymentChannel: PaymentChannel['MOBILEAPP'],
  freeField1: 'Shoes Cambridgeshire',
  freeField2: 'clicks-and-mortar bypass green',
  freeField3: 'Lodge Investment',
  createdAt: dayjs('2023-03-30'),
  updatedAt: dayjs('2023-03-30'),
};

export const sampleWithNewData: NewPayment = {
  recordUniqueIdentifier: 'fed07e1f-a8a8-44dd-ab11-124b9fb94bb5',
  returnMessage: 'Distributed Cameroon connect',
  feeId: 'invoice Oklahoma',
  firstName: 'Agnes',
  lastName: 'Lowe',
  middleName: 'mission-critical Games Personal',
  paymentCode: 'Account Accounts',
  schoolCode: 'solutions Adaptive Electronics',
  schoolName: 'Pakistan',
  studentClass: 'Money channels Ranch',
  paymentChannel: PaymentChannel['CHATBOT'],
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
