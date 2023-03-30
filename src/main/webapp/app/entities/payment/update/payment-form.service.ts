import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IPayment, NewPayment } from '../payment.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPayment for edit and NewPaymentFormGroupInput for create.
 */
type PaymentFormGroupInput = IPayment | PartialWithRequiredKeyOf<NewPayment>;

type PaymentFormDefaults = Pick<NewPayment, 'id'>;

type PaymentFormGroupContent = {
  id: FormControl<IPayment['id'] | NewPayment['id']>;
  recordUniqueIdentifier: FormControl<IPayment['recordUniqueIdentifier']>;
  returnCode: FormControl<IPayment['returnCode']>;
  returnMessage: FormControl<IPayment['returnMessage']>;
  processTimestamp: FormControl<IPayment['processTimestamp']>;
  feeAmount: FormControl<IPayment['feeAmount']>;
  feeDescription: FormControl<IPayment['feeDescription']>;
  feeDueFromDate: FormControl<IPayment['feeDueFromDate']>;
  feeDueToDate: FormControl<IPayment['feeDueToDate']>;
  feeId: FormControl<IPayment['feeId']>;
  dateOfBirth: FormControl<IPayment['dateOfBirth']>;
  firstName: FormControl<IPayment['firstName']>;
  lastName: FormControl<IPayment['lastName']>;
  middleName: FormControl<IPayment['middleName']>;
  outstandingAmount: FormControl<IPayment['outstandingAmount']>;
  paymentCode: FormControl<IPayment['paymentCode']>;
  schoolCode: FormControl<IPayment['schoolCode']>;
  schoolName: FormControl<IPayment['schoolName']>;
  studentClass: FormControl<IPayment['studentClass']>;
  paymentChannel: FormControl<IPayment['paymentChannel']>;
  freeField1: FormControl<IPayment['freeField1']>;
  freeField2: FormControl<IPayment['freeField2']>;
  freeField3: FormControl<IPayment['freeField3']>;
  createdAt: FormControl<IPayment['createdAt']>;
  updatedAt: FormControl<IPayment['updatedAt']>;
  student: FormControl<IPayment['student']>;
};

export type PaymentFormGroup = FormGroup<PaymentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PaymentFormService {
  createPaymentFormGroup(payment: PaymentFormGroupInput = { id: null }): PaymentFormGroup {
    const paymentRawValue = {
      ...this.getFormDefaults(),
      ...payment,
    };
    return new FormGroup<PaymentFormGroupContent>({
      id: new FormControl(
        { value: paymentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      recordUniqueIdentifier: new FormControl(paymentRawValue.recordUniqueIdentifier, {
        validators: [Validators.required],
      }),
      returnCode: new FormControl(paymentRawValue.returnCode, {
        validators: [Validators.maxLength(3)],
      }),
      returnMessage: new FormControl(paymentRawValue.returnMessage, {
        validators: [Validators.required, Validators.maxLength(50)],
      }),
      processTimestamp: new FormControl(paymentRawValue.processTimestamp),
      feeAmount: new FormControl(paymentRawValue.feeAmount),
      feeDescription: new FormControl(paymentRawValue.feeDescription, {
        validators: [Validators.minLength(3), Validators.maxLength(200)],
      }),
      feeDueFromDate: new FormControl(paymentRawValue.feeDueFromDate),
      feeDueToDate: new FormControl(paymentRawValue.feeDueToDate),
      feeId: new FormControl(paymentRawValue.feeId, {
        validators: [Validators.required, Validators.minLength(1), Validators.maxLength(50)],
      }),
      dateOfBirth: new FormControl(paymentRawValue.dateOfBirth),
      firstName: new FormControl(paymentRawValue.firstName, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(50)],
      }),
      lastName: new FormControl(paymentRawValue.lastName, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(50)],
      }),
      middleName: new FormControl(paymentRawValue.middleName, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(50)],
      }),
      outstandingAmount: new FormControl(paymentRawValue.outstandingAmount),
      paymentCode: new FormControl(paymentRawValue.paymentCode, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(50)],
      }),
      schoolCode: new FormControl(paymentRawValue.schoolCode, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(50)],
      }),
      schoolName: new FormControl(paymentRawValue.schoolName, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(200)],
      }),
      studentClass: new FormControl(paymentRawValue.studentClass, {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(50)],
      }),
      paymentChannel: new FormControl(paymentRawValue.paymentChannel, {
        validators: [Validators.required],
      }),
      freeField1: new FormControl(paymentRawValue.freeField1),
      freeField2: new FormControl(paymentRawValue.freeField2),
      freeField3: new FormControl(paymentRawValue.freeField3),
      createdAt: new FormControl(paymentRawValue.createdAt),
      updatedAt: new FormControl(paymentRawValue.updatedAt),
      student: new FormControl(paymentRawValue.student),
    });
  }

  getPayment(form: PaymentFormGroup): IPayment | NewPayment {
    return form.getRawValue() as IPayment | NewPayment;
  }

  resetForm(form: PaymentFormGroup, payment: PaymentFormGroupInput): void {
    const paymentRawValue = { ...this.getFormDefaults(), ...payment };
    form.reset(
      {
        ...paymentRawValue,
        id: { value: paymentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PaymentFormDefaults {
    return {
      id: null,
    };
  }
}
