import { TestBed, inject } from '@angular/core/testing';

import { CommonDeliveryService } from './common-delivery.service';

describe('CommonDeliveryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CommonDeliveryService]
    });
  });

  it('should be created', inject([CommonDeliveryService], (service: CommonDeliveryService) => {
    expect(service).toBeTruthy();
  }));
});
