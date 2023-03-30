import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { StudentClassComponent } from '../list/student-class.component';
import { StudentClassDetailComponent } from '../detail/student-class-detail.component';
import { StudentClassUpdateComponent } from '../update/student-class-update.component';
import { StudentClassRoutingResolveService } from './student-class-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const studentClassRoute: Routes = [
  {
    path: '',
    component: StudentClassComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StudentClassDetailComponent,
    resolve: {
      studentClass: StudentClassRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StudentClassUpdateComponent,
    resolve: {
      studentClass: StudentClassRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StudentClassUpdateComponent,
    resolve: {
      studentClass: StudentClassRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(studentClassRoute)],
  exports: [RouterModule],
})
export class StudentClassRoutingModule {}
