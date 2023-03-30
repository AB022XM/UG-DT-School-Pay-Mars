import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PaymentComponentsPage, PaymentDeleteDialog, PaymentUpdatePage } from './payment.page-object';

const expect = chai.expect;

describe('Payment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let paymentComponentsPage: PaymentComponentsPage;
  let paymentUpdatePage: PaymentUpdatePage;
  let paymentDeleteDialog: PaymentDeleteDialog;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth(username, password);
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Payments', async () => {
    await navBarPage.goToEntity('payment');
    paymentComponentsPage = new PaymentComponentsPage();
    await browser.wait(ec.visibilityOf(paymentComponentsPage.title), 5000);
    expect(await paymentComponentsPage.getTitle()).to.eq('schoolpayApp.payment.home.title');
    await browser.wait(ec.or(ec.visibilityOf(paymentComponentsPage.entities), ec.visibilityOf(paymentComponentsPage.noResult)), 1000);
  });

  it('should load create Payment page', async () => {
    await paymentComponentsPage.clickOnCreateButton();
    paymentUpdatePage = new PaymentUpdatePage();
    expect(await paymentUpdatePage.getPageTitle()).to.eq('schoolpayApp.payment.home.createOrEditLabel');
    await paymentUpdatePage.cancel();
  });

  it('should create and save Payments', async () => {
    const nbButtonsBeforeCreate = await paymentComponentsPage.countDeleteButtons();

    await paymentComponentsPage.clickOnCreateButton();

    await promise.all([
      paymentUpdatePage.setRecordUniqueIdentifierInput('64c99148-3908-465d-8c4a-e510e3ade974'),
      paymentUpdatePage.setReturnCodeInput('returnCode'),
      paymentUpdatePage.setReturnMessageInput('returnMessage'),
      paymentUpdatePage.setProcessTimestampInput('2000-12-31'),
      paymentUpdatePage.setFeeAmountInput('5'),
      paymentUpdatePage.setFeeDescriptionInput('feeDescription'),
      paymentUpdatePage.setFeeDueFromDateInput('2000-12-31'),
      paymentUpdatePage.setFeeDueToDateInput('2000-12-31'),
      paymentUpdatePage.setFeeIdInput('feeId'),
      paymentUpdatePage.setDateOfBirthInput('2000-12-31'),
      paymentUpdatePage.setFirstNameInput('firstName'),
      paymentUpdatePage.setLastNameInput('lastName'),
      paymentUpdatePage.setMiddleNameInput('middleName'),
      paymentUpdatePage.setOutstandingAmountInput('5'),
      paymentUpdatePage.setPaymentCodeInput('paymentCode'),
      paymentUpdatePage.setSchoolCodeInput('schoolCode'),
      paymentUpdatePage.setSchoolNameInput('schoolName'),
      paymentUpdatePage.setStudentClassInput('studentClass'),
      paymentUpdatePage.paymentChannelSelectLastOption(),
      paymentUpdatePage.setFreeField1Input('freeField1'),
      paymentUpdatePage.setFreeField2Input('freeField2'),
      paymentUpdatePage.setFreeField3Input('freeField3'),
      paymentUpdatePage.setCreatedAtInput('2000-12-31'),
      paymentUpdatePage.setUpdatedAtInput('2000-12-31'),
      paymentUpdatePage.studentSelectLastOption(),
    ]);

    await paymentUpdatePage.save();
    expect(await paymentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await paymentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Payment', async () => {
    const nbButtonsBeforeDelete = await paymentComponentsPage.countDeleteButtons();
    await paymentComponentsPage.clickOnLastDeleteButton();

    paymentDeleteDialog = new PaymentDeleteDialog();
    expect(await paymentDeleteDialog.getDialogTitle()).to.eq('schoolpayApp.payment.delete.question');
    await paymentDeleteDialog.clickOnConfirmButton();
    await browser.wait(ec.visibilityOf(paymentComponentsPage.title), 5000);

    expect(await paymentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
