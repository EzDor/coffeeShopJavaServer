import {Injectable} from '@angular/core';
import {NgbModal, NgbModalOptions, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {MessageService} from 'primeng/api';
import {CoreModule} from '../core.module';

@Injectable({
  providedIn: CoreModule
})

export class DialogService {

  private stack: NgbModalRef[] = [];


  constructor(private modalService: NgbModal, private messageService: MessageService) {
  }

  public openDialog(content: any, options?: NgbModalOptions): NgbModalRef {
    const dialog = this.modalService.open(content, options);

    this.stack.push(dialog);

    return dialog;
  }

  public showErrorMessage(error) {
    let errorMessage = 'Some error was occurred.';
    if (error) {
      errorMessage = error.message || error;
    }
    this.dismissDialog();
    this.messageService.add({key: 'global-error-tag', severity: 'error', summary: 'Error', detail: errorMessage, life: 5000});
  }

  public closeDialog(): void {
    this.stack.forEach(dialogRef => dialogRef.close());
    this.stack = [];
  }

  public dismissDialog(): void {
    this.stack.forEach(dialogRef => dialogRef.dismiss());
    this.stack = [];
  }

}
