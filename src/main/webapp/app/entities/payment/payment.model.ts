import dayjs from 'dayjs/esm';
import { IStudent } from 'app/entities/student/student.model';
import { PaymentChannel } from 'app/entities/enumerations/payment-channel.model';

export interface IPayment {
  id: number;
  recordUniqueIdentifier?: string | null;
  returnCode?: string | null;
  returnMessage?: string | null;
  processTimestamp?: dayjs.Dayjs | null;
  feeAmount?: number | null;
  feeDescription?: string | null;
  feeDueFromDate?: dayjs.Dayjs | null;
  feeDueToDate?: dayjs.Dayjs | null;
  feeId?: string | null;
  dateOfBirth?: dayjs.Dayjs | null;
  firstName?: string | null;
  lastName?: string | null;
  middleName?: string | null;
  outstandingAmount?: number | null;
  paymentCode?: string | null;
  schoolCode?: string | null;
  schoolName?: string | null;
  studentClass?: string | null;
  paymentChannel?: PaymentChannel | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  student?: Pick<IStudent, 'id'> | null;
}

export type NewPayment = Omit<IPayment, 'id'> & { id: null };
