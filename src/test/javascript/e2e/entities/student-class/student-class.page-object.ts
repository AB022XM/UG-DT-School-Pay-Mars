import { element, by, ElementFinder } from 'protractor';

export class StudentClassComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-student-class div table .btn-danger'));
  title = element.all(by.css('jhi-student-class div h2#page-heading span')).first();
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

export class StudentClassUpdatePage {
  pageTitle = element(by.id('jhi-student-class-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idInput = element(by.id('field_id'));
  recordUniqueIdentifierInput = element(by.id('field_recordUniqueIdentifier'));
  studentClassIdInput = element(by.id('field_studentClassId'));
  studentClassCodeInput = element(by.id('field_studentClassCode'));
  studentClassNameInput = element(by.id('field_studentClassName'));
  studentClassDescriptionInput = element(by.id('field_studentClassDescription'));
  statusInput = element(by.id('field_status'));
  freeField1Input = element(by.id('field_freeField1'));
  freeField2Input = element(by.id('field_freeField2'));
  freeField3Input = element(by.id('field_freeField3'));
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

  async setStudentClassIdInput(studentClassId: string): Promise<void> {
    await this.studentClassIdInput.sendKeys(studentClassId);
  }

  async getStudentClassIdInput(): Promise<string> {
    return await this.studentClassIdInput.getAttribute('value');
  }

  async setStudentClassCodeInput(studentClassCode: string): Promise<void> {
    await this.studentClassCodeInput.sendKeys(studentClassCode);
  }

  async getStudentClassCodeInput(): Promise<string> {
    return await this.studentClassCodeInput.getAttribute('value');
  }

  async setStudentClassNameInput(studentClassName: string): Promise<void> {
    await this.studentClassNameInput.sendKeys(studentClassName);
  }

  async getStudentClassNameInput(): Promise<string> {
    return await this.studentClassNameInput.getAttribute('value');
  }

  async setStudentClassDescriptionInput(studentClassDescription: string): Promise<void> {
    await this.studentClassDescriptionInput.sendKeys(studentClassDescription);
  }

  async getStudentClassDescriptionInput(): Promise<string> {
    return await this.studentClassDescriptionInput.getAttribute('value');
  }

  getStatusInput(): ElementFinder {
    return this.statusInput;
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

export class StudentClassDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-studentClass-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-studentClass'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
