import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductMenuGridComponent } from './product-menu-grid.component';

describe('ProductMenuGridComponent', () => {
  let component: ProductMenuGridComponent;
  let fixture: ComponentFixture<ProductMenuGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductMenuGridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductMenuGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
