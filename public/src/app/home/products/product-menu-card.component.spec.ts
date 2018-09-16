import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductMenuCardComponent } from './product-menu-card.component';

describe('ProductMenuCardComponent', () => {
  let component: ProductMenuCardComponent;
  let fixture: ComponentFixture<ProductMenuCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductMenuCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductMenuCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
