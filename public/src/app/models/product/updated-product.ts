import {Product} from './product';

export interface UpdatedProduct {
  updatedProductDetails: Product;
  productTypeToUpdate: string;
}
