import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { StudentClassComponentsPage, StudentClassDeleteDialog, StudentClassUpdatePage } from './student-class.page-object';

const expect = chai.expect;

describe('StudentClass e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let studentClassComponentsPage: StudentClassComponentsPage;
  let studentClassUpdatePage: StudentClassUpdatePage;
  let studentClassDeleteDialog: StudentClassDeleteDialog;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth(username, password);
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load StudentClasses', async () => {
    await navBarPage.goToEntity('student-class');
    studentClassComponentsPage = new StudentClassComponentsPage();
    await browser.wait(ec.visibilityOf(studentClassComponentsPage.title), 5000);
    expect(await studentClassComponentsPage.getTitle()).to.eq('schoolpayApp.studentClass.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(studentClassComponentsPage.entities), ec.visibilityOf(studentClassComponentsPage.noResult)),
      1000
    );
  });

  it('should load create StudentClass page', async () => {
    await studentClassComponentsPage.clickOnCreateButton();
    studentClassUpdatePage = new StudentClassUpdatePage();
    expect(await studentClassUpdatePage.getPageTitle()).to.eq('schoolpayApp.studentClass.home.createOrEditLabel');
    await studentClassUpdatePage.cancel();
  });

  it('should create and save StudentClasses', async () => {
    const nbButtonsBeforeCreate = await studentClassComponentsPage.countDeleteButtons();

    await studentClassComponentsPage.clickOnCreateButton();

    await promise.all([
      studentClassUpdatePage.setRecordUniqueIdentifierInput('64c99148-3908-465d-8c4a-e510e3ade974'),
      studentClassUpdatePage.setStudentClassIdInput('5'),
      studentClassUpdatePage.setStudentClassCodeInput('studentClassCode'),
      studentClassUpdatePage.setStudentClassNameInput('studentClassName'),
      studentClassUpdatePage.setStudentClassDescriptionInput('studentClassDescription'),
      studentClassUpdatePage.getStatusInput().click(),
      studentClassUpdatePage.setFreeField1Input('freeField1'),
      studentClassUpdatePage.setFreeField2Input('freeField2'),
      studentClassUpdatePage.setFreeField3Input('freeField3'),
      studentClassUpdatePage.setCreatedAtInput('2000-12-31'),
      studentClassUpdatePage.setUpdatedAtInput('2000-12-31'),
      studentClassUpdatePage.setIsDeletedInput('2000-12-31'),
    ]);

    await studentClassUpdatePage.save();
    expect(await studentClassUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await studentClassComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last StudentClass', async () => {
    const nbButtonsBeforeDelete = await studentClassComponentsPage.countDeleteButtons();
    await studentClassComponentsPage.clickOnLastDeleteButton();

    studentClassDeleteDialog = new StudentClassDeleteDialog();
    expect(await studentClassDeleteDialog.getDialogTitle()).to.eq('schoolpayApp.studentClass.delete.question');
    await studentClassDeleteDialog.clickOnConfirmButton();
    await browser.wait(ec.visibilityOf(studentClassComponentsPage.title), 5000);

    expect(await studentClassComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
