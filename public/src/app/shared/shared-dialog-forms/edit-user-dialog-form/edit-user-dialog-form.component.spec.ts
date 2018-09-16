import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserDialogFormComponent } from './edit-user-dialog-form.component';

describe('EditUserDialogFormComponent', () => {
  let component: EditUserDialogFormComponent;
  let fixture: ComponentFixture<EditUserDialogFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditUserDialogFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditUserDialogFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
