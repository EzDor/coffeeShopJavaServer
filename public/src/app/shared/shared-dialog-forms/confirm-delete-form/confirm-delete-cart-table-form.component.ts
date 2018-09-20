import { Component, OnInit } from '@angular/core';
import {DialogService} from '@services/dialog.service';
import {CartService} from '@services/cart.service';

@Component({
  selector: 'app-confirm-delete-cart-table-form',
  templateUrl: './confirm-delete-cart-table-form.component.html',
  styleUrls: ['./confirm-delete-cart-table-form.component.css']
})
export class ConfirmDeleteCartTableFormComponent implements OnInit {


  public loading: boolean;

  constructor(private dialogService: DialogService, private cartService: CartService) {
    this.loading = false;
  }

  ngOnInit() {
  }

  public submitForm(): void {
    this.loading = true;
    this.cartService.deleteSelectedItem().subscribe(
      () => this.onComplete(),
      error => this.showError(error)
    );
  }

  public dismiss(): void {
    this.dialogService.dismissDialog();
  }

  public close(): void {
    this.dialogService.closeDialog();
  }

  private onComplete(): void {
    this.cartService.refreshDataTable();
    this.loading = false;
    this.close();
  }

  private showError(error: any): void {
    throw error;
  }

}
