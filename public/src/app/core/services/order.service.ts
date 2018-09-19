import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Constants} from '@models/constants';
import {Observable} from 'rxjs';
import {Order} from '@models/cart/order';
import {OrderItem} from '@models/cart/order-item';
import {UpdatedOrderItem} from '@models/cart/updated-order-item';
import {CreditCard} from '@models/cart/credit-card';
import {CoreModule} from '@core/core.module';

@Injectable({providedIn: CoreModule})
export class OrderService {

  private readonly apiPrefix: string;

  constructor(private http: HttpClient) {
    this.apiPrefix = Constants.BASE_URL + Constants.API_PREFIX;
  }

  public getActiveCart(): Observable<Order> {
    return this.http.get<Order>(this.apiPrefix + Constants.GET_ACTIVE_CART_API_CALL);
  }

  public addItemToCart(orderItem: OrderItem): Observable<any> {
    return this.http.post(this.apiPrefix + Constants.ADD_ITEM_TO_CART_API_CALL, orderItem);
  }

  public getArchiveOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiPrefix + Constants.GET_ARCHIVE_ORDERS_FROM_CART_API_CALL);
  }

  public updateCart(updatedOrderItem: UpdatedOrderItem): Observable<any> {
    return this.http.post(this.apiPrefix + Constants.UPDATE_ITEM_ON_CART_API_CALL, updatedOrderItem);
  }

  public deleteItemFromCart(orderItemId: number): Observable<any> {
    return this.http.post(this.apiPrefix + Constants.DELETE_ITEM_FROM_CART_API_CALL, orderItemId);
  }

  public checkout(creditCard: CreditCard): Observable<any> {
    return this.http.post(this.apiPrefix + Constants.CHECKOUT_CART_API_CALL, creditCard);
  }
}
