import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteCartTableFormComponent } from './confirm-delete-cart-table-form.component';

describe('ConfirmDeleteCartTableFormComponent', () => {
  let component: ConfirmDeleteCartTableFormComponent;
  let fixture: ComponentFixture<ConfirmDeleteCartTableFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmDeleteCartTableFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmDeleteCartTableFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
