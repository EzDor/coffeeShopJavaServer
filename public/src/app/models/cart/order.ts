import {OrderStatus} from '@models/cart/order-status.enum';
import {OrderItem} from '@models/cart/order-item';


export interface Order {
  id?: number;
  orderStatus: OrderStatus;
  updateTime: string;
  creationTime: string;
  orderItems: OrderItem[];
  price?: number;
  displayDate?: string;
  orderItemProducts?: string[];
}
