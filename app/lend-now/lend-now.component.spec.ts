import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LendNowComponent } from './lend-now.component';

describe('LendNowComponent', () => {
  let component: LendNowComponent;
  let fixture: ComponentFixture<LendNowComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LendNowComponent]
    });
    fixture = TestBed.createComponent(LendNowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
