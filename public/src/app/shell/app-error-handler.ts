import {ErrorHandler, Injectable, Injector} from '@angular/core';
import {DialogService} from '../core/services/dialog.service';

@Injectable()
export class AppErrorHandler implements ErrorHandler {

  constructor(private injector: Injector) {

  }

  handleError(error: any): void {
    const dialogService = this.injector.get(DialogService);
    dialogService.showErrorMessage(error);
    console.log(error);
  }
}
