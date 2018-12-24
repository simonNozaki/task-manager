import { TestBed, inject } from '@angular/core/testing';

import { TaskLabelService } from './task-label.service';

describe('TaskLabelService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TaskLabelService]
    });
  });

  it('should be created', inject([TaskLabelService], (service: TaskLabelService) => {
    expect(service).toBeTruthy();
  }));
});
