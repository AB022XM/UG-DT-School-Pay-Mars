import { element, by, ElementFinder } from 'protractor';

export class PaymentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-payment div table .btn-danger'));
  title = element.all(by.css('jhi-payment div h2#page-heading span')).first();
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

export class PaymentUpdatePage {
  pageTitle = element(by.id('jhi-payment-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idInput = element(by.id('field_id'));
  recordUniqueIdentifierInput = element(by.id('field_recordUniqueIdentifier'));
  returnCodeInput = element(by.id('field_returnCode'));
  returnMessageInput = element(by.id('field_returnMessage'));
  processTimestampInput = element(by.id('field_processTimestamp'));
  feeAmountInput = element(by.id('field_feeAmount'));
  feeDescriptionInput = element(by.id('field_feeDescription'));
  feeDueFromDateInput = element(by.id('field_feeDueFromDate'));
  feeDueToDateInput = element(by.id('field_feeDueToDate'));
  feeIdInput = element(by.id('field_feeId'));
  dateOfBirthInput = element(by.id('field_dateOfBirth'));
  firstNameInput = element(by.id('field_firstName'));
  lastNameInput = element(by.id('field_lastName'));
  middleNameInput = element(by.id('field_middleName'));
  outstandingAmountInput = element(by.id('field_outstandingAmount'));
  paymentCodeInput = element(by.id('field_paymentCode'));
  schoolCodeInput = element(by.id('field_schoolCode'));
  schoolNameInput = element(by.id('field_schoolName'));
  studentClassInput = element(by.id('field_studentClass'));
  paymentChannelSelect = element(by.id('field_paymentChannel'));
  freeField1Input = element(by.id('field_freeField1'));
  freeField2Input = element(by.id('field_freeField2'));
  freeField3Input = element(by.id('field_freeField3'));
  createdAtInput = element(by.id('field_createdAt'));
  updatedAtInput = element(by.id('field_updatedAt'));

  studentSelect = element(by.id('field_student'));

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

  async setReturnCodeInput(returnCode: string): Promise<void> {
    await this.returnCodeInput.sendKeys(returnCode);
  }

  async getReturnCodeInput(): Promise<string> {
    return await this.returnCodeInput.getAttribute('value');
  }

  async setReturnMessageInput(returnMessage: string): Promise<void> {
    await this.returnMessageInput.sendKeys(returnMessage);
  }

  async getReturnMessageInput(): Promise<string> {
    return await this.returnMessageInput.getAttribute('value');
  }

  async setProcessTimestampInput(processTimestamp: string): Promise<void> {
    await this.processTimestampInput.sendKeys(processTimestamp);
  }

  async getProcessTimestampInput(): Promise<string> {
    return await this.processTimestampInput.getAttribute('value');
  }

  async setFeeAmountInput(feeAmount: string): Promise<void> {
    await this.feeAmountInput.sendKeys(feeAmount);
  }

  async getFeeAmountInput(): Promise<string> {
    return await this.feeAmountInput.getAttribute('value');
  }

  async setFeeDescriptionInput(feeDescription: string): Promise<void> {
    await this.feeDescriptionInput.sendKeys(feeDescription);
  }

  async getFeeDescriptionInput(): Promise<string> {
    return await this.feeDescriptionInput.getAttribute('value');
  }

  async setFeeDueFromDateInput(feeDueFromDate: string): Promise<void> {
    await this.feeDueFromDateInput.sendKeys(feeDueFromDate);
  }

  async getFeeDueFromDateInput(): Promise<string> {
    return await this.feeDueFromDateInput.getAttribute('value');
  }

  async setFeeDueToDateInput(feeDueToDate: string): Promise<void> {
    await this.feeDueToDateInput.sendKeys(feeDueToDate);
  }

  async getFeeDueToDateInput(): Promise<string> {
    return await this.feeDueToDateInput.getAttribute('value');
  }

  async setFeeIdInput(feeId: string): Promise<void> {
    await this.feeIdInput.sendKeys(feeId);
  }

  async getFeeIdInput(): Promise<string> {
    return await this.feeIdInput.getAttribute('value');
  }

  async setDateOfBirthInput(dateOfBirth: string): Promise<void> {
    await this.dateOfBirthInput.sendKeys(dateOfBirth);
  }

  async getDateOfBirthInput(): Promise<string> {
    return await this.dateOfBirthInput.getAttribute('value');
  }

  async setFirstNameInput(firstName: string): Promise<void> {
    await this.firstNameInput.sendKeys(firstName);
  }

  async getFirstNameInput(): Promise<string> {
    return await this.firstNameInput.getAttribute('value');
  }

  async setLastNameInput(lastName: string): Promise<void> {
    await this.lastNameInput.sendKeys(lastName);
  }

  async getLastNameInput(): Promise<string> {
    return await this.lastNameInput.getAttribute('value');
  }

  async setMiddleNameInput(middleName: string): Promise<void> {
    await this.middleNameInput.sendKeys(middleName);
  }

  async getMiddleNameInput(): Promise<string> {
    return await this.middleNameInput.getAttribute('value');
  }

  async setOutstandingAmountInput(outstandingAmount: string): Promise<void> {
    await this.outstandingAmountInput.sendKeys(outstandingAmount);
  }

  async getOutstandingAmountInput(): Promise<string> {
    return await this.outstandingAmountInput.getAttribute('value');
  }

  async setPaymentCodeInput(paymentCode: string): Promise<void> {
    await this.paymentCodeInput.sendKeys(paymentCode);
  }

  async getPaymentCodeInput(): Promise<string> {
    return await this.paymentCodeInput.getAttribute('value');
  }

  async setSchoolCodeInput(schoolCode: string): Promise<void> {
    await this.schoolCodeInput.sendKeys(schoolCode);
  }

  async getSchoolCodeInput(): Promise<string> {
    return await this.schoolCodeInput.getAttribute('value');
  }

  async setSchoolNameInput(schoolName: string): Promise<void> {
    await this.schoolNameInput.sendKeys(schoolName);
  }

  async getSchoolNameInput(): Promise<string> {
    return await this.schoolNameInput.getAttribute('value');
  }

  async setStudentClassInput(studentClass: string): Promise<void> {
    await this.studentClassInput.sendKeys(studentClass);
  }

  async getStudentClassInput(): Promise<string> {
    return await this.studentClassInput.getAttribute('value');
  }

  async setPaymentChannelSelect(paymentChannel: string): Promise<void> {
    await this.paymentChannelSelect.sendKeys(paymentChannel);
  }

  async getPaymentChannelSelect(): Promise<string> {
    return await this.paymentChannelSelect.element(by.css('option:checked')).getText();
  }

  async paymentChannelSelectLastOption(): Promise<void> {
    await this.paymentChannelSelect.all(by.tagName('option')).last().click();
  }

  async setFreeField1Input(freeField1: string): Promise<void> {
    await this.freeField1Input.sendKeys(freeField1);
  }

  async getFreeField1Input(): Promise<string> {
    return await this.freeField1Input.getAttribute('value');
  }

  async setFreeField2Input(freeField2: string): Promise<void> {
    await this.freeField2Input.sendKeys(freeField2);
  }

  async getFreeField2Input(): Promise<string> {
    return await this.freeField2Input.getAttribute('value');
  }

  async setFreeField3Input(freeField3: string): Promise<void> {
    await this.freeField3Input.sendKeys(freeField3);
  }

  async getFreeField3Input(): Promise<string> {
    return await this.freeField3Input.getAttribute('value');
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

  async studentSelectLastOption(): Promise<void> {
    await this.studentSelect.all(by.tagName('option')).last().click();
  }

  async studentSelectOption(option: string): Promise<void> {
    await this.studentSelect.sendKeys(option);
  }

  getStudentSelect(): ElementFinder {
    return this.studentSelect;
  }

  async getStudentSelectedOption(): Promise<string> {
    return await this.studentSelect.element(by.css('option:checked')).getText();
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

export class PaymentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-payment-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-payment'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
