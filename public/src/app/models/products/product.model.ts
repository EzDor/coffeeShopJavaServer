import {ProductStatus} from './product-status.enum';
import {Component} from '../components/component';

export interface Product {

  name: string;
  type: string;
  description: string;
  price: number;
  componentsTypes?: string[];
  components?: Component[];
  status: ProductStatus;
  image: string;

}
