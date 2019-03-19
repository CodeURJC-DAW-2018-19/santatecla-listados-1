import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherConceptViewComponent } from './teacher-concept-view.component';

describe('TeacherConceptViewComponent', () => {
  let component: TeacherConceptViewComponent;
  let fixture: ComponentFixture<TeacherConceptViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherConceptViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherConceptViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
