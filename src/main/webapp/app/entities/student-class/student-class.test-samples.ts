import dayjs from 'dayjs/esm';

import { IStudentClass, NewStudentClass } from './student-class.model';

export const sampleWithRequiredData: IStudentClass = {
  id: 89326,
  recordUniqueIdentifier: 'c958c94e-9fa0-40db-900d-2a48cf2b57a7',
  studentClassId: 49921,
  studentClassCode: 'Fresh',
  studentClassName: 'withdrawal Tasty',
  studentClassDescription: 'wireless Licensed Balboa',
  status: false,
};

export const sampleWithPartialData: IStudentClass = {
  id: 5106,
  recordUniqueIdentifier: '8d34a24e-5c5d-4952-91a1-42806c272c8c',
  studentClassId: 7256,
  studentClassCode: 'Designer models',
  studentClassName: 'secondary',
  studentClassDescription: 'Configurable withdrawal',
  status: false,
  freeField1: 'interface',
  createdAt: dayjs('2023-03-29'),
  isDeleted: dayjs('2023-03-29'),
};

export const sampleWithFullData: IStudentClass = {
  id: 79467,
  recordUniqueIdentifier: '376c7eec-7022-4ea7-9423-35795a00edcd',
  studentClassId: 5425,
  studentClassCode: 'Automotive capacitor Executive',
  studentClassName: 'withdrawal',
  studentClassDescription: 'Avon Organized Enhanced',
  status: false,
  freeField1: 'collaboration real-time',
  freeField2: 'Officer card Berkshire',
  freeField3: 'Estate Island',
  createdAt: dayjs('2023-03-29'),
  updatedAt: dayjs('2023-03-30'),
  isDeleted: dayjs('2023-03-30'),
};

export const sampleWithNewData: NewStudentClass = {
  recordUniqueIdentifier: 'f12f54a0-f222-4b24-972d-3139dd1dbb7f',
  studentClassId: 49159,
  studentClassCode: 'Checking',
  studentClassName: 'revolutionary New',
  studentClassDescription: 'initiatives Gibraltar',
  status: true,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
