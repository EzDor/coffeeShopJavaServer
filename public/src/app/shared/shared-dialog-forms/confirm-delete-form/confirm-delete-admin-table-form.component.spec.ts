import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {ConfirmDeleteAdminTableFormComponent} from '@dialogs/confirm-delete-form/confirm-delete-admin-table-form.component';


describe('ConfirmDeleteAdminTableFormComponent', () => {
  let component: ConfirmDeleteAdminTableFormComponent;
  let fixture: ComponentFixture<ConfirmDeleteAdminTableFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ConfirmDeleteAdminTableFormComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmDeleteAdminTableFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
