import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDataTableComponent } from './admin-data-table.component';

describe('AdminDataTableComponent', () => {
  let component: AdminDataTableComponent;
  let fixture: ComponentFixture<AdminDataTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminDataTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDataTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
