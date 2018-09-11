import {Injectable} from '@angular/core';
import {CoreModule} from '../core.module';

@Injectable({
  providedIn: CoreModule
})
export class StorageService {

  constructor() {
  }

  public getStorageData(key: string): any {
    const storageDataString: string = localStorage.getItem(key);
    return JSON.parse(storageDataString);
  }

  public setStorageData(key: string, storageData: any): void {
    const storageDataString = JSON.stringify(storageData);
    localStorage.setItem(key, storageDataString);
  }
}
