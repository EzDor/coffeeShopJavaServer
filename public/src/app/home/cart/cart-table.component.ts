import {Component, OnInit} from '@angular/core';
import {NgbModalOptions} from '@ng-bootstrap/ng-bootstrap';
import {BehaviorSubject} from 'rxjs';
import {DialogService} from '@services/dialog.service';
import {EditComponentDialogFormComponent} from '@dialogs/edit-component-dialog-form/edit-component-dialog-form.component';
import {CartService} from '@services/cart.service';
import {CartTabs} from '@models/cart/cart-tabs.enum';
import {OrderItemDisplayKeys} from '@models/cart/order-item-display-keys';
import {NewOrderDialogFormComponent} from '@dialogs/new-order-dialog-form/new-order-dialog-form.component';
import {ConfirmDeleteCartTableFormComponent} from '@dialogs/confirm-delete-form/confirm-delete-cart-table-form.component';
import {CheckoutDialogFormComponent} from '@dialogs/checkout-dialog-form/checkout-dialog-form.component';
import {Constants} from '@models/constants';
import {OrderDisplayKeys} from '@models/cart/order-display-keys';

@Component({
  selector: 'app-cart-table',
  templateUrl: './cart-table.component.html',
  styleUrls: ['./cart-table.component.css']
})
export class CartTableComponent implements OnInit {

  private readonly cartDialogOptions: NgbModalOptions;
  private _displayData$: BehaviorSubject<any[]>;
  private currentTab$: BehaviorSubject<CartTabs>;
  private _searchBy$: BehaviorSubject<string>;
  private _rowDisplayKeys$: BehaviorSubject<OrderItemDisplayKeys | OrderDisplayKeys>;

  constructor(private cartService: CartService, private dialogService: DialogService) {
    this.cartDialogOptions = {centered: true, size: 'lg'};
  }

  ngOnInit() {
    this.currentTab$ = this.cartService.currentTab;
    this._displayData$ = this.cartService.currentTableData;
    this._searchBy$ = this.cartService.searchBy;
    this._rowDisplayKeys$ = this.cartService.rowDisplayKeys;
  }

  public get displayData$(): BehaviorSubject<any[]> {
    return this._displayData$;
  }

  public get searchBy$(): BehaviorSubject<string> {
    return this._searchBy$;
  }

  public get rowDisplayKeys$(): OrderItemDisplayKeys | OrderDisplayKeys {
    return this._rowDisplayKeys$.value;
  }

  public showDialog(id?: number): void {
    this.cartService.updateSelectedItemById(id);
    switch (this.currentTab$.getValue()) {

      case CartTabs.Cart:
        this.dialogService.openDialog(NewOrderDialogFormComponent, this.cartDialogOptions);
        break;

      case CartTabs.History:
        this.dialogService.openDialog(EditComponentDialogFormComponent, this.cartDialogOptions);
        break;

      default:
        throw new Error('Some data is missing try to refresh the page');

    }
  }

  public showDeleteDialog(id: number) {
    this.cartService.updateSelectedItemById(id);
    this.dialogService.openDialog(ConfirmDeleteCartTableFormComponent);
  }

  public showCheckoutDialog() {
    this.dialogService.openDialog(CheckoutDialogFormComponent);
  }

  public isCartTab(): boolean {
    return this.currentTab$.value === CartTabs.Cart;
  }

  public isPayable(): boolean {
    return this.displayData$.value.length > 0 && this.isCartTab();
  }

  public getOpenDialogButtonText(): string {
    return this.isCartTab() ? Constants.CART_TABLE_PRIMARY_BUTTON_KEY : Constants.CART_TABLE_HISTORY_BUTTON_KEY;
  }

}
