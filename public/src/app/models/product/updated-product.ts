import {Product} from './product.model';

export interface UpdatedProduct {
  updatedProductDetails: Product;
  productTypeToUpdate: string;
}
