import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentConceptViewComponent } from './student-concept-view.component';

describe('StudentConceptViewComponent', () => {
  let component: StudentConceptViewComponent;
  let fixture: ComponentFixture<StudentConceptViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentConceptViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentConceptViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
