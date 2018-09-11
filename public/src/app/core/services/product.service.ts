import {Injectable} from '@angular/core';
import {Constants} from '../../models/constants';
import {HttpClient} from '@angular/common/http';
import {Product} from '../../models/product.model';
import {Observable} from 'rxjs';
import {CoreModule} from '../core.module';

@Injectable({
  providedIn: CoreModule
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(Constants.BASE_URL + Constants.API_PREFIX + Constants.GET_PRODUCTS_API_CALL);
  }
}
