import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AdminTabs} from '../../models/admin/admin-tabs.enum';
import {DialogService} from '../../core/services/dialog.service';
import {EditUserDialogFormComponent} from '../../shared/shared-dialog-forms/edit-user-dialog-form/edit-user-dialog-form.component';
import {AdminService} from '../../core/services/admin.service';
import {NgbModalOptions} from '@ng-bootstrap/ng-bootstrap';
import {ConfirmDeleteFormComponent} from '../../shared/shared-dialog-forms/confirm-delete-form/confirm-delete-form.component';
import {EditComponentDialogFormComponent} from '../../shared/shared-dialog-forms/edit-component-dialog-form/edit-component-dialog-form.component';
import {EditProductDialogFormComponent} from '../../shared/shared-dialog-forms/edit-product-dialog-form/edit-product-dialog-form.component';


@Component({
  selector: 'app-admin-data-table',
  templateUrl: './admin-data-table.component.html',
  styleUrls: ['./admin-data-table.component.css']
})
export class AdminDataTableComponent implements OnInit {

  private readonly adminDialogOptions: NgbModalOptions;

  private _displayData$: BehaviorSubject<any[]>;

  private currentTab$: BehaviorSubject<AdminTabs>;

  private _searchBy$: BehaviorSubject<string>;

  constructor(private adminService: AdminService, private dialogService: DialogService) {
    this.adminDialogOptions = {centered: true};
  }

  ngOnInit() {
    this.currentTab$ = this.adminService.currentTab;
    this._displayData$ = this.adminService.currentTableData;
    this._searchBy$ = this.adminService.searchBy;

    // open dialog auto to develop....
    // this.displayData$.subscribe(data => {
    //     this.showDialog();
    // });
  }

  get displayData$(): BehaviorSubject<any[]> {
    return this._displayData$;
  }

  get searchBy$(): BehaviorSubject<string> {
    return this._searchBy$;
  }

  public showDialog(id?: number): void {
    this.adminService.updateSelectedRow(id);
    switch (this.currentTab$.getValue()) {

      case AdminTabs.product:
        this.dialogService.openDialog(EditProductDialogFormComponent, this.adminDialogOptions);
        break;

      case AdminTabs.component:
        this.dialogService.openDialog(EditComponentDialogFormComponent, this.adminDialogOptions);
        break;

      case AdminTabs.user:
        this.dialogService.openDialog(EditUserDialogFormComponent, this.adminDialogOptions);
        break;

      default:
        throw new Error('Some data is missing try to refresh the page');

    }
  }

  public showDeleteDialog(id: number) {
    this.adminService.updateSelectedRow(id);
    this.dialogService.openDialog(ConfirmDeleteFormComponent);
  }
}
