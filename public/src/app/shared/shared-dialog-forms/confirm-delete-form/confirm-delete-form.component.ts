import {Component, OnInit} from '@angular/core';
import {DialogService} from '../../../core/services/dialog.service';
import {AdminService} from '../../../core/services/admin.service';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-confirm-delete-form',
  templateUrl: './confirm-delete-form.component.html',
  styleUrls: ['./confirm-delete-form.component.css']
})
export class ConfirmDeleteFormComponent implements OnInit {

  public loading: boolean;

  constructor(private dialogService: DialogService, private adminService: AdminService) {
    this.loading = false;
  }

  ngOnInit() {
  }

  public submitForm(): void {
    this.loading = true;
    this.adminService.deleteSelectedRow().subscribe(
      res => this.onComplete(),
      error => this.showError(error)
    );
  }

  public dismiss(): void {
    this.dialogService.dismissDialog();
  }

  public close(): void {
    this.dialogService.closeDialog();
  }

  private onComplete(): void {
    this.loading = false;
    this.close();
    this.adminService.refreshDataTable();
  }

  private showError(error: any): void {
    throw error;
  }

}
