import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasklineComponent } from './taskline.component';

describe('TasklineComponent', () => {
  let component: TasklineComponent;
  let fixture: ComponentFixture<TasklineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasklineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasklineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
