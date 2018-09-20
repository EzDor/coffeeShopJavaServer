import {Component} from '@models/component/component';
import {Product} from '@models/product/product';

export interface OrderItem {
  id?: number;
  product?: Product;
  components?: Component[];
  componentsTypes?: string[];
  productType?: string;
  price?: number;
}
