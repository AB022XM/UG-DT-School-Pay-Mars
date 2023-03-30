import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStudentClass } from '../student-class.model';
import { StudentClassService } from '../service/student-class.service';

@Injectable({ providedIn: 'root' })
export class StudentClassRoutingResolveService implements Resolve<IStudentClass | null> {
  constructor(protected service: StudentClassService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStudentClass | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((studentClass: HttpResponse<IStudentClass>) => {
          if (studentClass.body) {
            return of(studentClass.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
