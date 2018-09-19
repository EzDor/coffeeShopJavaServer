import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewOrderDialogFormComponent } from './new-order-dialog-form.component';

describe('NewOrderDialogFormComponent', () => {
  let component: NewOrderDialogFormComponent;
  let fixture: ComponentFixture<NewOrderDialogFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewOrderDialogFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewOrderDialogFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
