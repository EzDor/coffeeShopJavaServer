import {Component, OnInit} from '@angular/core';
import {Constants} from '../../model/constants';
import {ProductService} from './product.service';
import {Product} from '../../model/product.model';

@Component({
  selector: 'app-product-menu-grid',
  templateUrl: './product-menu-grid.component.html',
  styleUrls: ['./product-menu-grid.component.css']
})


export class ProductMenuGridComponent implements OnInit {

  public gridCols: number;
  public rowHeight: number;
  private products: Product[];

  tiles: Tile[] = [
    {text: 'One', cols: Constants.NUM_OF_GRID_COL_FOR_CARD, rows: Constants.NUM_OF_GRID_ROWS_FOR_CARD, color: 'lightblue'},
    {text: 'Two', cols: Constants.NUM_OF_GRID_COL_FOR_CARD, rows: Constants.NUM_OF_GRID_ROWS_FOR_CARD, color: 'lightgreen'},
    {text: 'Three', cols: Constants.NUM_OF_GRID_COL_FOR_CARD, rows: Constants.NUM_OF_GRID_ROWS_FOR_CARD, color: 'lightpink'},
    {text: 'Four', cols: Constants.NUM_OF_GRID_COL_FOR_CARD, rows: Constants.NUM_OF_GRID_ROWS_FOR_CARD, color: '#DDBDF1'},
    {text: 'Five', cols: Constants.NUM_OF_GRID_COL_FOR_CARD, rows: Constants.NUM_OF_GRID_ROWS_FOR_CARD, color: 'blue'},
  ];

  constructor(private productService: ProductService) {
    this.gridCols = Constants.NUM_OF_GRID_COL;
    this.rowHeight = Constants.GRID_HEIGHT;
  }

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(
        products => {
          this.products = products;
          console.log(products);
        }
      );
  }

}

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}
