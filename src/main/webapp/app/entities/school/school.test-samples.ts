import dayjs from 'dayjs/esm';

import { ISchool, NewSchool } from './school.model';

export const sampleWithRequiredData: ISchool = {
  id: 82208,
  recordUniqueIdentifier: 'f8185c2d-858f-44c9-a3e7-fb81dd39adb2',
  schoolId: 72443,
  schoolCode: 'Rubber maroon',
  schoolName: 'Sausages',
  status: false,
};

export const sampleWithPartialData: ISchool = {
  id: 47266,
  recordUniqueIdentifier: 'ae906b19-48c5-462f-96d8-cde3506e6d0d',
  schoolId: 5732,
  schoolCode: 'Group application up',
  schoolPhoneNumber: 'Integration Soft Rubber',
  schoolemailAddess: 'Soap Mississippi',
  schoolName: 'Exclusive Configuration',
  status: false,
  freeField1: 'Bike turquoise grow',
  freeField2: 'Outdoors',
  updatedAt: dayjs('2023-03-30'),
  isDeleted: dayjs('2023-03-30'),
};

export const sampleWithFullData: ISchool = {
  id: 7835,
  recordUniqueIdentifier: 'f172055b-bb67-4156-8b30-37ca72f18f34',
  schoolId: 69717,
  schoolCode: '5th partnerships Sports',
  schoolPhoneNumber: 'withdrawal Configuration Lucia',
  schoolAlternativePhoneNumber: '1080p digital Berkshire',
  schoolemailAddess: 'SCSI mesh Puerto',
  schoolName: 'Fresh Response',
  status: false,
  freeField1: 'Auto schemas',
  freeField2: 'Baby',
  freeField3: 'Gorgeous',
  createdAt: dayjs('2023-03-29'),
  updatedAt: dayjs('2023-03-30'),
  isDeleted: dayjs('2023-03-30'),
};

export const sampleWithNewData: NewSchool = {
  recordUniqueIdentifier: 'c31b40d5-d489-4da4-be55-b8da43ec05f7',
  schoolId: 89786,
  schoolCode: 'Planner synergies',
  schoolName: 'Nigeria primary Trafficway',
  status: false,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
