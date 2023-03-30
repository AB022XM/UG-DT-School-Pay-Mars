import dayjs from 'dayjs/esm';

export interface IStudentClass {
  id: number;
  recordUniqueIdentifier?: string | null;
  studentClassId?: number | null;
  studentClassCode?: string | null;
  studentClassName?: string | null;
  studentClassDescription?: string | null;
  status?: boolean | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  isDeleted?: dayjs.Dayjs | null;
}

export type NewStudentClass = Omit<IStudentClass, 'id'> & { id: null };
