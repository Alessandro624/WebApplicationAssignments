import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RistorantiHomeComponent } from './ristoranti-home.component';

describe('RistorantiHomeComponent', () => {
  let component: RistorantiHomeComponent;
  let fixture: ComponentFixture<RistorantiHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RistorantiHomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RistorantiHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
