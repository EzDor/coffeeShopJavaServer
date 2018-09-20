import {Component, OnInit} from '@angular/core';
import {DialogService} from '@services/dialog.service';
import {AdminService} from '@services/admin.service';

@Component({
  selector: 'app-confirm-delete-admin-table-form',
  templateUrl: './confirm-delete-admin-table-form.component.html',
  styleUrls: ['./confirm-delete-admin-table-form.component.css']
})
export class ConfirmDeleteAdminTableFormComponent implements OnInit {

  public loading: boolean;

  constructor(private dialogService: DialogService, private adminService: AdminService) {
    this.loading = false;
  }

  ngOnInit() {
  }

  public submitForm(): void {
    this.loading = true;
    this.adminService.deleteSelectedRow().subscribe(
      () => this.onComplete(),
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
    this.adminService.refreshDataTable();
    this.loading = false;
    this.close();
  }

  private showError(error: any): void {
    throw error;
  }

}
