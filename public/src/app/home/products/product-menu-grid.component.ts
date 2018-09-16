import {Component, OnInit} from '@angular/core';
import {Constants} from '../../models/constants';
import {ProductService} from '../../core/services/product.service';
import {Product} from '../../models/product/product';

@Component({
  selector: 'app-product-menu-grid',
  templateUrl: './product-menu-grid.component.html',
  styleUrls: ['./product-menu-grid.component.css']
})


export class ProductMenuGridComponent implements OnInit {

  public gridCols: number;
  public rowHeight: number;
  public cardCols: number;
  public cardRows: number;
  public products: Product[];

  constructor(private productService: ProductService) {
    this.gridCols = Constants.NUM_OF_GRID_COL;
    this.rowHeight = Constants.GRID_HEIGHT;
    this.cardCols = Constants.NUM_OF_GRID_COL_FOR_CARD;
    this.cardRows = Constants.NUM_OF_GRID_ROWS_FOR_CARD;
  }

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(
        products => {
          this.products = products;
        }
      );
  }

}
