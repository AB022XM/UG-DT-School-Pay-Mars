import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'student',
        data: { pageTitle: 'schoolpayApp.student.home.title' },
        loadChildren: () => import('./student/student.module').then(m => m.StudentModule),
      },
      {
        path: 'student-class',
        data: { pageTitle: 'schoolpayApp.studentClass.home.title' },
        loadChildren: () => import('./student-class/student-class.module').then(m => m.StudentClassModule),
      },
      {
        path: 'contact-info',
        data: { pageTitle: 'schoolpayApp.contactInfo.home.title' },
        loadChildren: () => import('./contact-info/contact-info.module').then(m => m.ContactInfoModule),
      },
      {
        path: 'address',
        data: { pageTitle: 'schoolpayApp.address.home.title' },
        loadChildren: () => import('./address/address.module').then(m => m.AddressModule),
      },
      {
        path: 'school',
        data: { pageTitle: 'schoolpayApp.school.home.title' },
        loadChildren: () => import('./school/school.module').then(m => m.SchoolModule),
      },
      {
        path: 'payment',
        data: { pageTitle: 'schoolpayApp.payment.home.title' },
        loadChildren: () => import('./payment/payment.module').then(m => m.PaymentModule),
      },
      {
        path: 'associated-fees',
        data: { pageTitle: 'schoolpayApp.associatedFees.home.title' },
        loadChildren: () => import('./associated-fees/associated-fees.module').then(m => m.AssociatedFeesModule),
      },
      {
        path: 'payment-channel',
        data: { pageTitle: 'schoolpayApp.paymentChannel.home.title' },
        loadChildren: () => import('./payment-channel/payment-channel.module').then(m => m.PaymentChannelModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
