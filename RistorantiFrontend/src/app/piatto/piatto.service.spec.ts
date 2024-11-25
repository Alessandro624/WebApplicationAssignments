import { TestBed } from '@angular/core/testing';

import { PiattoService } from './piatto.service';

describe('PiattoService', () => {
  let service: PiattoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PiattoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
