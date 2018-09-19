import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {Constants} from '@models/constants';
import {CartTabs} from '@models/cart/cart-tabs.enum';
import {CartService} from '@services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public cartTabs: MenuItem[];
  public activeItem: MenuItem;
  public defaultTab: CartTabs;

  constructor(private cartService: CartService) {
  }

  ngOnInit() {
    this.defaultTab = this.cartService.defaultTab;
    this.cartTabs = [
      {
        label: Constants.CART_TAB_LABEL,
        icon: Constants.CART_TAB_ICON,
        command: () => this.updateCurrentTab(CartTabs.Cart)
      },
      {
        label: Constants.CART_TAB_HISTORY_LABEL,
        icon: Constants.CART_TAB_HISTORY_ICON,
        command: () => this.updateCurrentTab(CartTabs.History)
      }
    ];

    this.activeItem = this.cartTabs[this.defaultTab];
  }

  public updateCurrentTab(tab: CartTabs) {
    this.cartService.updateTab(tab);
  }


}
