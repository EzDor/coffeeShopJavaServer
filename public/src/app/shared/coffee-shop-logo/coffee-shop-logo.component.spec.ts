import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoffeeShopLogoComponent } from './coffee-shop-logo.component';

describe('CoffeeShopLogoComponent', () => {
  let component: CoffeeShopLogoComponent;
  let fixture: ComponentFixture<CoffeeShopLogoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoffeeShopLogoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoffeeShopLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
