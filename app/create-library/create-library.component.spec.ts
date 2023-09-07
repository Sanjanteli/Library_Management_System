import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLibraryComponent } from './create-library.component';

describe('CreateLibraryComponent', () => {
  let component: CreateLibraryComponent;
  let fixture: ComponentFixture<CreateLibraryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateLibraryComponent]
    });
    fixture = TestBed.createComponent(CreateLibraryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
