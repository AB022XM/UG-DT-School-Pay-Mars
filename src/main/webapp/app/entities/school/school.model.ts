import dayjs from 'dayjs/esm';

export interface ISchool {
  id: number;
  recordUniqueIdentifier?: string | null;
  schoolId?: number | null;
  schoolCode?: string | null;
  schoolPhoneNumber?: string | null;
  schoolAlternativePhoneNumber?: string | null;
  schoolemailAddess?: string | null;
  schoolName?: string | null;
  status?: boolean | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  isDeleted?: dayjs.Dayjs | null;
}

export type NewSchool = Omit<ISchool, 'id'> & { id: null };
