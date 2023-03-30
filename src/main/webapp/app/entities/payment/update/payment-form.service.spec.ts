import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../payment.test-samples';

import { PaymentFormService } from './payment-form.service';

describe('Payment Form Service', () => {
  let service: PaymentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaymentFormService);
  });

  describe('Service methods', () => {
    describe('createPaymentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPaymentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            recordUniqueIdentifier: expect.any(Object),
            returnCode: expect.any(Object),
            returnMessage: expect.any(Object),
            processTimestamp: expect.any(Object),
            feeAmount: expect.any(Object),
            feeDescription: expect.any(Object),
            feeDueFromDate: expect.any(Object),
            feeDueToDate: expect.any(Object),
            feeId: expect.any(Object),
            dateOfBirth: expect.any(Object),
            firstName: expect.any(Object),
            lastName: expect.any(Object),
            middleName: expect.any(Object),
            outstandingAmount: expect.any(Object),
            paymentCode: expect.any(Object),
            schoolCode: expect.any(Object),
            schoolName: expect.any(Object),
            studentClass: expect.any(Object),
            paymentChannel: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
            freeField3: expect.any(Object),
            createdAt: expect.any(Object),
            updatedAt: expect.any(Object),
            student: expect.any(Object),
          })
        );
      });

      it('passing IPayment should create a new form with FormGroup', () => {
        const formGroup = service.createPaymentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            recordUniqueIdentifier: expect.any(Object),
            returnCode: expect.any(Object),
            returnMessage: expect.any(Object),
            processTimestamp: expect.any(Object),
            feeAmount: expect.any(Object),
            feeDescription: expect.any(Object),
            feeDueFromDate: expect.any(Object),
            feeDueToDate: expect.any(Object),
            feeId: expect.any(Object),
            dateOfBirth: expect.any(Object),
            firstName: expect.any(Object),
            lastName: expect.any(Object),
            middleName: expect.any(Object),
            outstandingAmount: expect.any(Object),
            paymentCode: expect.any(Object),
            schoolCode: expect.any(Object),
            schoolName: expect.any(Object),
            studentClass: expect.any(Object),
            paymentChannel: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
            freeField3: expect.any(Object),
            createdAt: expect.any(Object),
            updatedAt: expect.any(Object),
            student: expect.any(Object),
          })
        );
      });
    });

    describe('getPayment', () => {
      it('should return NewPayment for default Payment initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createPaymentFormGroup(sampleWithNewData);

        const payment = service.getPayment(formGroup) as any;

        expect(payment).toMatchObject(sampleWithNewData);
      });

      it('should return NewPayment for empty Payment initial value', () => {
        const formGroup = service.createPaymentFormGroup();

        const payment = service.getPayment(formGroup) as any;

        expect(payment).toMatchObject({});
      });

      it('should return IPayment', () => {
        const formGroup = service.createPaymentFormGroup(sampleWithRequiredData);

        const payment = service.getPayment(formGroup) as any;

        expect(payment).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPayment should not enable id FormControl', () => {
        const formGroup = service.createPaymentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPayment should disable id FormControl', () => {
        const formGroup = service.createPaymentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
