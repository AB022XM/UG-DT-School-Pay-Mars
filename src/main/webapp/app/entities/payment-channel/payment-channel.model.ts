import dayjs from 'dayjs/esm';

export interface IPaymentChannel {
  id: number;
  recordUniqueIdentifier?: string | null;
  channelId?: number | null;
  channelCode?: number | null;
  channelName?: number | null;
  status?: boolean | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  isDeleted?: boolean | null;
}

export type NewPaymentChannel = Omit<IPaymentChannel, 'id'> & { id: null };
