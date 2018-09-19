import {Component, OnInit} from '@angular/core';
import {Constants} from '@models/constants';
import {ProductService} from '@services/product.service';
import {Product} from '@models/product/product';
import {NewOrderDialogFormComponent} from '@dialogs/new-order-dialog-form/new-order-dialog-form.component';
import {DialogService} from '@services/dialog.service';

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

  constructor(private productService: ProductService, private dialogService: DialogService) {
    this.gridCols = Constants.NUM_OF_GRID_COL;
    this.rowHeight = Constants.GRID_HEIGHT;
    this.cardCols = Constants.NUM_OF_GRID_COL_FOR_CARD;
    this.cardRows = Constants.NUM_OF_GRID_ROWS_FOR_CARD;
  }

  ngOnInit() {
    this.productService.getActiveProducts()
      .subscribe(
        (products: Product[]) => {
          this.products = products;
        }
      );
  }

  private beLazy() {
    this.productService.selectedProduct = this.products[0];
    this.dialogService.openDialog(NewOrderDialogFormComponent, {centered: true, size: 'lg'});
  }

}
