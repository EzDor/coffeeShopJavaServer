import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckoutDialogFormComponent } from './checkout-dialog-form.component';

describe('CheckoutDialogFormComponent', () => {
  let component: CheckoutDialogFormComponent;
  let fixture: ComponentFixture<CheckoutDialogFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckoutDialogFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckoutDialogFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
