import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IPaymentChannel, NewPaymentChannel } from '../payment-channel.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPaymentChannel for edit and NewPaymentChannelFormGroupInput for create.
 */
type PaymentChannelFormGroupInput = IPaymentChannel | PartialWithRequiredKeyOf<NewPaymentChannel>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IPaymentChannel | NewPaymentChannel> = Omit<T, 'createdAt'> & {
  createdAt?: string | null;
};

type PaymentChannelFormRawValue = FormValueOf<IPaymentChannel>;

type NewPaymentChannelFormRawValue = FormValueOf<NewPaymentChannel>;

type PaymentChannelFormDefaults = Pick<NewPaymentChannel, 'id' | 'status' | 'createdAt' | 'isDeleted'>;

type PaymentChannelFormGroupContent = {
  id: FormControl<PaymentChannelFormRawValue['id'] | NewPaymentChannel['id']>;
  recordUniqueIdentifier: FormControl<PaymentChannelFormRawValue['recordUniqueIdentifier']>;
  channelId: FormControl<PaymentChannelFormRawValue['channelId']>;
  channelCode: FormControl<PaymentChannelFormRawValue['channelCode']>;
  channelName: FormControl<PaymentChannelFormRawValue['channelName']>;
  status: FormControl<PaymentChannelFormRawValue['status']>;
  freeField1: FormControl<PaymentChannelFormRawValue['freeField1']>;
  freeField2: FormControl<PaymentChannelFormRawValue['freeField2']>;
  freeField3: FormControl<PaymentChannelFormRawValue['freeField3']>;
  createdAt: FormControl<PaymentChannelFormRawValue['createdAt']>;
  updatedAt: FormControl<PaymentChannelFormRawValue['updatedAt']>;
  isDeleted: FormControl<PaymentChannelFormRawValue['isDeleted']>;
};

export type PaymentChannelFormGroup = FormGroup<PaymentChannelFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PaymentChannelFormService {
  createPaymentChannelFormGroup(paymentChannel: PaymentChannelFormGroupInput = { id: null }): PaymentChannelFormGroup {
    const paymentChannelRawValue = this.convertPaymentChannelToPaymentChannelRawValue({
      ...this.getFormDefaults(),
      ...paymentChannel,
    });
    return new FormGroup<PaymentChannelFormGroupContent>({
      id: new FormControl(
        { value: paymentChannelRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      recordUniqueIdentifier: new FormControl(paymentChannelRawValue.recordUniqueIdentifier, {
        validators: [Validators.required],
      }),
      channelId: new FormControl(paymentChannelRawValue.channelId, {
        validators: [Validators.required],
      }),
      channelCode: new FormControl(paymentChannelRawValue.channelCode, {
        validators: [Validators.required],
      }),
      channelName: new FormControl(paymentChannelRawValue.channelName, {
        validators: [Validators.required],
      }),
      status: new FormControl(paymentChannelRawValue.status, {
        validators: [Validators.required],
      }),
      freeField1: new FormControl(paymentChannelRawValue.freeField1),
      freeField2: new FormControl(paymentChannelRawValue.freeField2),
      freeField3: new FormControl(paymentChannelRawValue.freeField3),
      createdAt: new FormControl(paymentChannelRawValue.createdAt),
      updatedAt: new FormControl(paymentChannelRawValue.updatedAt),
      isDeleted: new FormControl(paymentChannelRawValue.isDeleted),
    });
  }

  getPaymentChannel(form: PaymentChannelFormGroup): IPaymentChannel | NewPaymentChannel {
    return this.convertPaymentChannelRawValueToPaymentChannel(
      form.getRawValue() as PaymentChannelFormRawValue | NewPaymentChannelFormRawValue
    );
  }

  resetForm(form: PaymentChannelFormGroup, paymentChannel: PaymentChannelFormGroupInput): void {
    const paymentChannelRawValue = this.convertPaymentChannelToPaymentChannelRawValue({ ...this.getFormDefaults(), ...paymentChannel });
    form.reset(
      {
        ...paymentChannelRawValue,
        id: { value: paymentChannelRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PaymentChannelFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      status: false,
      createdAt: currentTime,
      isDeleted: false,
    };
  }

  private convertPaymentChannelRawValueToPaymentChannel(
    rawPaymentChannel: PaymentChannelFormRawValue | NewPaymentChannelFormRawValue
  ): IPaymentChannel | NewPaymentChannel {
    return {
      ...rawPaymentChannel,
      createdAt: dayjs(rawPaymentChannel.createdAt, DATE_TIME_FORMAT),
    };
  }

  private convertPaymentChannelToPaymentChannelRawValue(
    paymentChannel: IPaymentChannel | (Partial<NewPaymentChannel> & PaymentChannelFormDefaults)
  ): PaymentChannelFormRawValue | PartialWithRequiredKeyOf<NewPaymentChannelFormRawValue> {
    return {
      ...paymentChannel,
      createdAt: paymentChannel.createdAt ? paymentChannel.createdAt.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
