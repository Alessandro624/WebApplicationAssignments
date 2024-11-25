import { TestBed } from '@angular/core/testing';

import { RistoranteService } from './ristorante.service';

describe('RistoranteService', () => {
  let service: RistoranteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RistoranteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
