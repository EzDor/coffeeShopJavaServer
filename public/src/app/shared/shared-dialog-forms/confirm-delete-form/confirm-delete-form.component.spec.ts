import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteFormComponent } from './confirm-delete-form.component';

describe('ConfirmDeleteFormComponent', () => {
  let component: ConfirmDeleteFormComponent;
  let fixture: ComponentFixture<ConfirmDeleteFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmDeleteFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmDeleteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
