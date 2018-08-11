import {Injectable} from '@angular/core';
import {ProductModule} from './product.module';
import {Constants} from '../../model/constants';
import {HttpClient} from '@angular/common/http';
import {Product} from '../../model/product.model';
import {Observable} from 'rxjs';

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) {
  }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(Constants.BASE_URL + Constants.API_PREFIX + Constants.GET_PRODUCTS_API_CALL);
  }
}
