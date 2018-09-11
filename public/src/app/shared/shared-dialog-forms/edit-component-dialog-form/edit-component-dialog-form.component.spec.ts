import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditComponentDialogFormComponent } from './edit-component-dialog-form.component';

describe('EditComponentDialogFormComponent', () => {
  let component: EditComponentDialogFormComponent;
  let fixture: ComponentFixture<EditComponentDialogFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditComponentDialogFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditComponentDialogFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
