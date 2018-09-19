import {Component, OnInit} from '@angular/core';
import {NgbModalOptions} from '@ng-bootstrap/ng-bootstrap';
import {BehaviorSubject} from 'rxjs';
import {ProductDisplayKeys} from '@models/product/product-display-keys';
import {DialogService} from '@services/dialog.service';
import {EditProductDialogFormComponent} from '@dialogs/edit-product-dialog-form/edit-product-dialog-form.component';
import {EditComponentDialogFormComponent} from '@dialogs/edit-component-dialog-form/edit-component-dialog-form.component';
import {ConfirmDeleteFormComponent} from '@dialogs/confirm-delete-form/confirm-delete-form.component';
import {CartService} from '@services/cart.service';
import {CartTabs} from '@models/cart/cart-tabs.enum';
import {OrderItemDisplayKeys} from '@models/cart/order-item-display-keys';
import {NewOrderDialogFormComponent} from '@dialogs/new-order-dialog-form/new-order-dialog-form.component';

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

  private _rowDisplayKeys$: BehaviorSubject<OrderItemDisplayKeys | ProductDisplayKeys>;

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

  public get rowDisplayKeys$(): OrderItemDisplayKeys | ProductDisplayKeys {
    return this._rowDisplayKeys$.value;
  }

  public showDialog(id?: number): void {
    this.cartService.updateSelectedRowById(id);
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
    this.cartService.updateSelectedRowById(id);
    this.dialogService.openDialog(ConfirmDeleteFormComponent);
  }

  public isCartTab(): boolean {
    return this.currentTab$.value === CartTabs.Cart;
  }

  // TODO constants
  getOpenDialogButtonText(): string {
    return this.isCartTab() ? 'Edit' : 'More Details';
  }

}
