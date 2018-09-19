import {OrderStatus} from '@models/cart/order-status.enum';
import {OrderItem} from '@models/cart/order-item';


export interface Order {
  orderStatus: OrderStatus;
  updateTime: Date;
  creationTime: Date;
  orderItems: OrderItem[];
}
