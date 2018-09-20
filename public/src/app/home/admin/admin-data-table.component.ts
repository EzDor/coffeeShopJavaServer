import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AdminTabs} from '@models/admin/admin-tabs.enum';
import {DialogService} from '@services/dialog.service';
import {EditUserDialogFormComponent} from '@dialogs/edit-user-dialog-form/edit-user-dialog-form.component';
import {AdminService} from 'src/app/core/services/admin.service';
import {NgbModalOptions} from '@ng-bootstrap/ng-bootstrap';
import {ComponentDisplayKeys} from '@models/component/component-display-keys';
import {ProductDisplayKeys} from '@models/product/product-display-keys';
import {UserDisplayKeys} from '@models/user/user-display-keys';
import {EditProductDialogFormComponent} from '@dialogs/edit-product-dialog-form/edit-product-dialog-form.component';
import {EditComponentDialogFormComponent} from '@dialogs/edit-component-dialog-form/edit-component-dialog-form.component';
import {ConfirmDeleteAdminTableFormComponent} from '@dialogs/confirm-delete-form/confirm-delete-admin-table-form.component';


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

  private _rowDisplayKeys$: BehaviorSubject<ComponentDisplayKeys | ProductDisplayKeys | UserDisplayKeys>;

  constructor(private adminService: AdminService, private dialogService: DialogService) {
    this.adminDialogOptions = {centered: true};
  }

  ngOnInit() {
    this.currentTab$ = this.adminService.currentTab;
    this._displayData$ = this.adminService.currentTableData;
    this._searchBy$ = this.adminService.searchBy;
    this._rowDisplayKeys$ = this.adminService.rowDisplayKeys;
  }

  public get displayData$(): BehaviorSubject<any[]> {
    return this._displayData$;
  }

  public get searchBy$(): BehaviorSubject<string> {
    return this._searchBy$;
  }

  public get rowDisplayKeys$(): ComponentDisplayKeys | ProductDisplayKeys | UserDisplayKeys {
    return this._rowDisplayKeys$.value;
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
    this.dialogService.openDialog(ConfirmDeleteAdminTableFormComponent);
  }
}
