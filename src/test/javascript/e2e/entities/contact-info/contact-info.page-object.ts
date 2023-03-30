import { element, by, ElementFinder } from 'protractor';

export class ContactInfoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-contact-info div table .btn-danger'));
  title = element.all(by.css('jhi-contact-info div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ContactInfoUpdatePage {
  pageTitle = element(by.id('jhi-contact-info-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idInput = element(by.id('field_id'));
  recordUniqueIdentifierInput = element(by.id('field_recordUniqueIdentifier'));
  contactIdInput = element(by.id('field_contactId'));
  phoneNumberInput = element(by.id('field_phoneNumber'));
  emailAddressInput = element(by.id('field_emailAddress'));
  parentsPhoneNumberInput = element(by.id('field_parentsPhoneNumber'));
  createdAtInput = element(by.id('field_createdAt'));
  updatedAtInput = element(by.id('field_updatedAt'));
  isDeletedInput = element(by.id('field_isDeleted'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdInput(id: string): Promise<void> {
    await this.idInput.sendKeys(id);
  }

  async getIdInput(): Promise<string> {
    return await this.idInput.getAttribute('value');
  }

  async setRecordUniqueIdentifierInput(recordUniqueIdentifier: string): Promise<void> {
    await this.recordUniqueIdentifierInput.sendKeys(recordUniqueIdentifier);
  }

  async getRecordUniqueIdentifierInput(): Promise<string> {
    return await this.recordUniqueIdentifierInput.getAttribute('value');
  }

  async setContactIdInput(contactId: string): Promise<void> {
    await this.contactIdInput.sendKeys(contactId);
  }

  async getContactIdInput(): Promise<string> {
    return await this.contactIdInput.getAttribute('value');
  }

  async setPhoneNumberInput(phoneNumber: string): Promise<void> {
    await this.phoneNumberInput.sendKeys(phoneNumber);
  }

  async getPhoneNumberInput(): Promise<string> {
    return await this.phoneNumberInput.getAttribute('value');
  }

  async setEmailAddressInput(emailAddress: string): Promise<void> {
    await this.emailAddressInput.sendKeys(emailAddress);
  }

  async getEmailAddressInput(): Promise<string> {
    return await this.emailAddressInput.getAttribute('value');
  }

  async setParentsPhoneNumberInput(parentsPhoneNumber: string): Promise<void> {
    await this.parentsPhoneNumberInput.sendKeys(parentsPhoneNumber);
  }

  async getParentsPhoneNumberInput(): Promise<string> {
    return await this.parentsPhoneNumberInput.getAttribute('value');
  }

  async setCreatedAtInput(createdAt: string): Promise<void> {
    await this.createdAtInput.sendKeys(createdAt);
  }

  async getCreatedAtInput(): Promise<string> {
    return await this.createdAtInput.getAttribute('value');
  }

  async setUpdatedAtInput(updatedAt: string): Promise<void> {
    await this.updatedAtInput.sendKeys(updatedAt);
  }

  async getUpdatedAtInput(): Promise<string> {
    return await this.updatedAtInput.getAttribute('value');
  }

  async setIsDeletedInput(isDeleted: string): Promise<void> {
    await this.isDeletedInput.sendKeys(isDeleted);
  }

  async getIsDeletedInput(): Promise<string> {
    return await this.isDeletedInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ContactInfoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-contactInfo-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-contactInfo'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
