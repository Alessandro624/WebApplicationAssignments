import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PiattoDetailsComponent } from './piatto-details.component';

describe('PiattoDetailsComponent', () => {
  let component: PiattoDetailsComponent;
  let fixture: ComponentFixture<PiattoDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PiattoDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PiattoDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
