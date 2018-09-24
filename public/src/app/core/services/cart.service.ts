import {Injectable} from '@angular/core';
import {CoreModule} from '@core/core.module';
import {Constants} from '@models/constants';
import {BehaviorSubject, Observable} from 'rxjs';
import {UserService} from '@services/user.service';
import {ProductService} from '@services/product.service';
import {ComponentsService} from '@services/components.service';
import {OrderService} from '@services/order.service';
import {CartTabs} from '@models/cart/cart-tabs.enum';
import {Order} from '@models/cart/order';
import {OrderItem} from '@models/cart/order-item';
import {OrderItemDisplayKeys} from '@models/cart/order-item-display-keys';
import {OrderDisplayKeys} from '@models/cart/order-display-keys';
import {DatePipe} from '@angular/common';

@Injectable({providedIn: CoreModule})
export class CartService {

  private _currentTab: BehaviorSubject<CartTabs>;
  private _currentTableData: BehaviorSubject<any[]>;
  private _rowDisplayKeys: BehaviorSubject<OrderItemDisplayKeys | OrderDisplayKeys>;
  private _searchBy: BehaviorSubject<string>;
  private _selectedItem: OrderItem | Order;
  private readonly _defaultTab: CartTabs;
  private readonly datePipe: DatePipe;

  constructor(
    private userService: UserService,
    private productService: ProductService,
    private componentsService: ComponentsService,
    private orderService: OrderService) {
    this._defaultTab = CartTabs.Cart;
    this.initDefaults();
    this.subscribeToData();
    this.datePipe = new DatePipe('en-US');
  }

  public updateTab(tab: CartTabs) {
    this._currentTab.next(tab);
  }


  public get currentTab(): BehaviorSubject<CartTabs> {
    return this._currentTab;
  }

  public get currentTableData(): BehaviorSubject<Order[] | OrderItem[]> {
    return this._currentTableData;
  }

  public get selectedItem(): Order | OrderItem {
    return this._selectedItem;
  }

  public set selectedItem(value: OrderItem | Order) {
    this._selectedItem = value;
  }

  public get rowDisplayKeys(): BehaviorSubject<OrderItemDisplayKeys | OrderDisplayKeys> {
    return this._rowDisplayKeys;
  }

  public updateSelectedItemById(id?: number): void {
    if (id) {
      const tableDataArray: any[] = this._currentTableData.getValue();
      this._selectedItem = tableDataArray.find(x => x.id === id);
    }
    else {
      this._selectedItem = null;
    }
  }

  public deleteSelectedItem(): Observable<any> {
    if (this.currentTab.value !== CartTabs.Cart) {
      throw new Error('Some data is missing try to refresh the page');
    }
    return this.deleteItemFromCart();
  }

  public get defaultTab(): CartTabs {
    return this._defaultTab;
  }

  public get searchBy(): BehaviorSubject<string> {
    return this._searchBy;
  }

  public refreshDataTable(): void {
    this.updateTableData(this._currentTab.getValue());
  }


  /*********************************
   * Private Functions
   *********************************/

  private subscribeToData(): void {
    this._currentTab.subscribe((tab: CartTabs) => {
      this.updateTableData(tab);
    });
  }

  private updateTableData(currentTab: CartTabs) {
    switch (currentTab) {

      case CartTabs.Cart:
        this.initCartTable();
        break;

      case CartTabs.History:
        this.initHistoryTable();
        break;

      default:
        throw new Error('Error while trying to update data table.');
    }
  }

  private initCartTable(): void {
    this._searchBy.next(Constants.CART_TABLE_SEARCH_KEY);
    this._rowDisplayKeys.next(Constants.ORDER_ITEM_DISPLAY_KEYS);
    this.orderService.getActiveCart()
      .subscribe(
        (order: Order) => {
          this.mapOrderItems(order.orderItems);
          order.orderItems.sort((x, y) => x.id - y.id);
          this._currentTableData.next(order.orderItems);
        });
  }

  private initHistoryTable(): void {
    this._searchBy.next(Constants.CART_TABLE_SEARCH_KEY_HISTORY);
    this._rowDisplayKeys.next(Constants.ORDER_DISPLAY_KEYS);
    this.orderService.getArchiveOrders()
      .subscribe(
        (orders: Order[]) => {
          this.mapOrders(orders);
          orders.sort((x, y) => {
            return new Date(x.updateTime).getDate() - new Date(y.updateTime).getDate();
          });
          this._currentTableData.next(orders);
        }
      );
  }

  private mapOrderItems(orderItems: OrderItem[]) {
    orderItems.map(orderItem => {
      orderItem.componentsTypes = orderItem.components.map(component => component.type);
      orderItem.productType = orderItem.product.type;
    });
  }

  private initDefaults(): void {
    this._searchBy = new BehaviorSubject<string>(Constants.CART_TABLE_SEARCH_KEY);
    this._currentTableData = new BehaviorSubject<OrderItem[] | Order[]>([]);
    this._currentTab = new BehaviorSubject<CartTabs>(this._defaultTab);
    this._rowDisplayKeys = new BehaviorSubject<OrderItemDisplayKeys | OrderDisplayKeys>(Constants.ORDER_ITEM_DISPLAY_KEYS);
  }

  private deleteItemFromCart(): Observable<any> {
    const orderItem: OrderItem = <OrderItem> this.selectedItem;
    return this.orderService.deleteItemFromCart(orderItem.id);
  }

  private mapOrders(orders: Order[]) {
    orders.map(
      (order: Order) => {
        order.displayDate = this.datePipe.transform(order.updateTime);
        order.price = order.orderItems.map(
          orderItem => orderItem.price).reduce(
          (x, y) => {
            return x + y;
          });
        order.orderItemProducts = order.orderItems.map(
          (orderItem: OrderItem) => orderItem.product.name);
      }
    );
  }
}
