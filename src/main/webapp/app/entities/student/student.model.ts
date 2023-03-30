import dayjs from 'dayjs/esm';
import { IStudentClass } from 'app/entities/student-class/student-class.model';
import { ISchool } from 'app/entities/school/school.model';

export interface IStudent {
  id: number;
  recordUniqueIdentifier?: string | null;
  studentId?: number | null;
  firstName?: string | null;
  middleName?: string | null;
  lastName?: string | null;
  paymentCode?: string | null;
  dateOfBirth?: dayjs.Dayjs | null;
  outStandingAmount?: string | null;
  status?: boolean | null;
  studentContact?: string | null;
  studentAddress?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  isDeleted?: dayjs.Dayjs | null;
  studentClass?: Pick<IStudentClass, 'id'> | null;
  school?: Pick<ISchool, 'id'> | null;
}

export type NewStudent = Omit<IStudent, 'id'> & { id: null };
