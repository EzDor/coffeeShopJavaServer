import {OrderItem} from '@models/cart/order-item';

export interface UpdatedOrderItem {
  orderItemId: number;
  orderDetails: OrderItem;

}
