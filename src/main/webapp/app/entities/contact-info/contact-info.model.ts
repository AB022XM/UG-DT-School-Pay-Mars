import dayjs from 'dayjs/esm';

export interface IContactInfo {
  id: number;
  recordUniqueIdentifier?: string | null;
  contactId?: string | null;
  phoneNumber?: string | null;
  emailAddress?: string | null;
  parentsPhoneNumber?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  isDeleted?: dayjs.Dayjs | null;
}

export type NewContactInfo = Omit<IContactInfo, 'id'> & { id: null };
