import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../models/product/product';
import {DialogService} from '../../core/services/dialog.service';
import {NewOrderDialogFormComponent} from '../../shared/shared-dialog-forms/new-order-dialog-form/new-order-dialog-form.component';
import {NgbModalOptions} from '@ng-bootstrap/ng-bootstrap';
import {ProductService} from '../../core/services/product.service';

@Component({
  selector: 'app-product-menu-card',
  templateUrl: './product-menu-card.component.html',
  styleUrls: ['./product-menu-card.component.css']
})
export class ProductMenuCardComponent implements OnInit {

  @Input() product: Product;

  private readonly newOrderDialogOptions: NgbModalOptions;

  constructor(private dialogService: DialogService, private productService: ProductService) {
    this.newOrderDialogOptions = {centered: true, size: 'lg'};
  }

  ngOnInit() {
  }

  public openOrderDialog() {
    this.productService.selectedProduct = this.product;
    this.dialogService.openDialog(NewOrderDialogFormComponent, this.newOrderDialogOptions);
  }

}
