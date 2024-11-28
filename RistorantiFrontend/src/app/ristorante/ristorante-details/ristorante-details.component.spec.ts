import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RistoranteDetailsComponent } from './ristorante-details.component';

describe('RistoranteDetailsComponent', () => {
  let component: RistoranteDetailsComponent;
  let fixture: ComponentFixture<RistoranteDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RistoranteDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RistoranteDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
