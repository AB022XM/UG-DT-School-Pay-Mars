import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IContactInfo, NewContactInfo } from '../contact-info.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IContactInfo for edit and NewContactInfoFormGroupInput for create.
 */
type ContactInfoFormGroupInput = IContactInfo | PartialWithRequiredKeyOf<NewContactInfo>;

type ContactInfoFormDefaults = Pick<NewContactInfo, 'id'>;

type ContactInfoFormGroupContent = {
  id: FormControl<IContactInfo['id'] | NewContactInfo['id']>;
  recordUniqueIdentifier: FormControl<IContactInfo['recordUniqueIdentifier']>;
  contactId: FormControl<IContactInfo['contactId']>;
  phoneNumber: FormControl<IContactInfo['phoneNumber']>;
  emailAddress: FormControl<IContactInfo['emailAddress']>;
  parentsPhoneNumber: FormControl<IContactInfo['parentsPhoneNumber']>;
  createdAt: FormControl<IContactInfo['createdAt']>;
  updatedAt: FormControl<IContactInfo['updatedAt']>;
  isDeleted: FormControl<IContactInfo['isDeleted']>;
};

export type ContactInfoFormGroup = FormGroup<ContactInfoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ContactInfoFormService {
  createContactInfoFormGroup(contactInfo: ContactInfoFormGroupInput = { id: null }): ContactInfoFormGroup {
    const contactInfoRawValue = {
      ...this.getFormDefaults(),
      ...contactInfo,
    };
    return new FormGroup<ContactInfoFormGroupContent>({
      id: new FormControl(
        { value: contactInfoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      recordUniqueIdentifier: new FormControl(contactInfoRawValue.recordUniqueIdentifier, {
        validators: [Validators.required],
      }),
      contactId: new FormControl(contactInfoRawValue.contactId),
      phoneNumber: new FormControl(contactInfoRawValue.phoneNumber),
      emailAddress: new FormControl(contactInfoRawValue.emailAddress),
      parentsPhoneNumber: new FormControl(contactInfoRawValue.parentsPhoneNumber),
      createdAt: new FormControl(contactInfoRawValue.createdAt),
      updatedAt: new FormControl(contactInfoRawValue.updatedAt),
      isDeleted: new FormControl(contactInfoRawValue.isDeleted),
    });
  }

  getContactInfo(form: ContactInfoFormGroup): IContactInfo | NewContactInfo {
    return form.getRawValue() as IContactInfo | NewContactInfo;
  }

  resetForm(form: ContactInfoFormGroup, contactInfo: ContactInfoFormGroupInput): void {
    const contactInfoRawValue = { ...this.getFormDefaults(), ...contactInfo };
    form.reset(
      {
        ...contactInfoRawValue,
        id: { value: contactInfoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ContactInfoFormDefaults {
    return {
      id: null,
    };
  }
}
