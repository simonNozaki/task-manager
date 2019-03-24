import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasklabelComponent } from './tasklabel.component';

describe('TasklabelComponent', () => {
  let component: TasklabelComponent;
  let fixture: ComponentFixture<TasklabelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasklabelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasklabelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
