import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PiattiHomeComponent } from './piatti-home.component';

describe('PiattiHomeComponent', () => {
  let component: PiattiHomeComponent;
  let fixture: ComponentFixture<PiattiHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PiattiHomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PiattiHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
