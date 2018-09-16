import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../models/product/product';

@Component({
  selector: 'app-product-menu-card',
  templateUrl: './product-menu-card.component.html',
  styleUrls: ['./product-menu-card.component.css']
})
export class ProductMenuCardComponent implements OnInit {

  @Input() product: Product;

  constructor() {
  }

  ngOnInit() {
  }

  public openOrderDialog() {
    console.log('DORRRRRRR');
  }

}
