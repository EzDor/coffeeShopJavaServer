import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProductDialogFormComponent } from './edit-product-dialog-form.component';

describe('EditProductDialogFormComponent', () => {
  let component: EditProductDialogFormComponent;
  let fixture: ComponentFixture<EditProductDialogFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditProductDialogFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditProductDialogFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
