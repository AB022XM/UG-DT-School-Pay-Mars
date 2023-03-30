import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStudentClass } from '../student-class.model';

@Component({
  selector: 'jhi-student-class-detail',
  templateUrl: './student-class-detail.component.html',
})
export class StudentClassDetailComponent implements OnInit {
  studentClass: IStudentClass | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ studentClass }) => {
      this.studentClass = studentClass;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
