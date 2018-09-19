import {Injectable} from '@angular/core';
import {CoreModule} from '../core.module';
import {HttpClient} from '@angular/common/http';
import {Constants} from '@models/constants';
import {Observable} from 'rxjs';
import {Component} from '@models/component/component';
import {UpdateComponent} from '@models/component/update-component';

@Injectable({
  providedIn: CoreModule
})
export class ComponentsService {

  private readonly apiPrefix: string;

  constructor(private http: HttpClient) {
    this.apiPrefix = Constants.BASE_URL + Constants.API_PREFIX;
  }

  public getComponents(): Observable<Component[]> {
    return this.http.get<Component []>(this.apiPrefix + Constants.GET_COMPONENTS_API_CALL);
  }

  public createComponent(component: Component) {
    return this.http.post(this.apiPrefix + Constants.CREATE_COMPONENTS_API_CALL, component);
  }

  public updateComponent(updatedComponent: UpdateComponent) {
    return this.http.post(this.apiPrefix + Constants.UPDATE_COMPONENTS_API_CALL, updatedComponent);
  }

  public deleteComponent(componentType: string) {
    return this.http.post(this.apiPrefix + Constants.DELETE_COMPONENTS_API_CALL, componentType);
  }

}
