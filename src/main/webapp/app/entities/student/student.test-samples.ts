import dayjs from 'dayjs/esm';

import { IStudent, NewStudent } from './student.model';

export const sampleWithRequiredData: IStudent = {
  id: 23105,
  recordUniqueIdentifier: 'da4cf75e-65a1-4e59-9051-cda5b83a83c5',
  studentId: 39489,
  firstName: 'Isom',
  middleName: 'of neural',
  lastName: "O'Reilly",
  paymentCode: 'B2C',
  dateOfBirth: dayjs('2023-03-30'),
  outStandingAmount: 'Sleek Pi',
  status: false,
};

export const sampleWithPartialData: IStudent = {
  id: 65618,
  recordUniqueIdentifier: '6c7d7aa7-e1e0-4bfb-9cec-18090c3e3594',
  studentId: 2371,
  firstName: 'Quinn',
  middleName: 'Pennsylvania SDD Unbranded',
  lastName: 'Kunde',
  paymentCode: 'magenta overriding C',
  dateOfBirth: dayjs('2023-03-29'),
  outStandingAmount: 'Consulta',
  status: true,
  studentContact: 'Vision-oriented',
  studentAddress: 'Investor calculating',
  freeField2: 'system',
  freeField3: 'homogeneous Group',
};

export const sampleWithFullData: IStudent = {
  id: 57685,
  recordUniqueIdentifier: '251f008a-84e3-4bc0-ba7f-faa1317cf9b3',
  studentId: 25914,
  firstName: 'Wayne',
  middleName: 'Jewelery',
  lastName: 'Gutkowski',
  paymentCode: 'deliver',
  dateOfBirth: dayjs('2023-03-30'),
  outStandingAmount: 'override',
  status: false,
  studentContact: 'Buckinghamshire THX Sausages',
  studentAddress: 'up blue teal',
  freeField1: 'Practical Credit',
  freeField2: 'Dollar Consultant User-friendly',
  freeField3: 'Pants yellow Concrete',
  createdAt: dayjs('2023-03-30'),
  updatedAt: dayjs('2023-03-30'),
  isDeleted: dayjs('2023-03-30'),
};

export const sampleWithNewData: NewStudent = {
  recordUniqueIdentifier: 'cfe92cb8-d6a1-4215-aa62-c01ed62911d9',
  studentId: 13499,
  firstName: 'Otto',
  middleName: 'sexy user mesh',
  lastName: 'Rutherford',
  paymentCode: 'Dynamic',
  dateOfBirth: dayjs('2023-03-29'),
  outStandingAmount: 'Granite',
  status: false,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
