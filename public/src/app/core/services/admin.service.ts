import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from 'src/app/models/users/user.model';
import {Product} from 'src/app/models/products/product.model';
import {Component} from 'src/app/models/components/component';
import {AdminTabs} from 'src/app/models/admin/admin-tabs.enum';
import {UserService} from 'src/app/core/services/user.service';
import {ProductService} from 'src/app/core/services/product.service';
import {Constants} from 'src/app/models/constants';
import {CoreModule} from '../core.module';
import {ComponentsService} from './components.service';

@Injectable({providedIn: CoreModule})
export class AdminService {

  private _currentTab: BehaviorSubject<AdminTabs>;
  private _currentTableData: BehaviorSubject<any[]>;
  private _searchBy: BehaviorSubject<string>;
  private _selectedRow: User | Product | Component;
  private readonly _defaultTab: AdminTabs;

  constructor(
    private userService: UserService,
    private productService: ProductService,
    private componentsService: ComponentsService) {
    this._defaultTab = AdminTabs.product;
    this.initDefaults();
    this.subscribeToData();
  }

  public updateTab(tab: AdminTabs) {
    this._currentTab.next(tab);
  }


  public get currentTab(): BehaviorSubject<AdminTabs> {
    return this._currentTab;
  }

  public get currentTableData(): BehaviorSubject<User[] | Product[] | Component[]> {
    return this._currentTableData;
  }

  public get selectedRow(): User | Product | Component {
    return this._selectedRow;
  }

  public updateSelectedRow(id?: number): void {
    if (id) {
      const tableDataArray: any[] = this._currentTableData.getValue();
      this._selectedRow = tableDataArray.find(x => x.id === id);
    }
    else {
      this._selectedRow = null;
    }
  }

  public deleteSelectedRow(): Observable<any> {
    switch (this._currentTab.getValue()) {

      case AdminTabs.user:
        return this.deleteUser();

      case AdminTabs.component:
        return this.deleteComponent();

      case AdminTabs.product:
        return this.deleteProduct();

      default:
        throw new Error('Some data is missing try to refresh the page');
    }
  }

  public get defaultTab(): AdminTabs {
    return this._defaultTab;
  }

  public get searchBy(): BehaviorSubject<string> {
    return this._searchBy;
  }

  public refreshDataTable(): void {
    this.updateTableData(this._currentTab.getValue());
  }


  /*********************************
   * Private Functions
   *********************************/

  private subscribeToData(): void {
    this._currentTab.subscribe((tab: AdminTabs) => {
      this.updateTableData(tab);
    });
  }

  private updateTableData(currentTab: AdminTabs) {
    switch (currentTab) {

      case AdminTabs.user:
        this.initUsersTable();
        break;

      case AdminTabs.component:
        this.initComponentsTable();
        break;

      case AdminTabs.product:
        this.initProductsTable();
        break;

      default:
        throw Error('Error while trying to update data table.')
    }
  }

  private initUsersTable(): void {
    this._searchBy.next(Constants.ADMIN_TABLE_SEARCH_KEY_USERS);
    this.userService.users.subscribe(
      (users: User[]) => {
        this._currentTableData.next(users);
      });
  }

  private initProductsTable(): void {
    this._searchBy.next(Constants.ADMIN_TABLE_SEARCH_KEY_PRODUCT);
    this.productService.getProducts().subscribe(
      (products: Product[]) => {
        this._currentTableData.next(products);
      }
    );
  }

  private initComponentsTable(): void {
    this._searchBy.next(Constants.ADMIN_TABLE_SEARCH_KEY_COMPONENT);
    this.componentsService.getComponents().subscribe(
      (components: Component[]) => {
        this._currentTableData.next(components);
      }
    );
  }

  private initDefaults(): void {
    this._searchBy = new BehaviorSubject<string>(Constants.ADMIN_TABLE_SEARCH_KEY_PRODUCT);
    this._currentTableData = new BehaviorSubject<User[] | Product[] | Component[]>([]);
    this._currentTab = new BehaviorSubject<AdminTabs>(this._defaultTab);
  }

  private deleteUser(): Observable<any> {
    const user: User = <User> this._selectedRow;
    return this.userService.deleteUser(user.username);
  }

  private deleteProduct(): Observable<any> {
    const user: User = <User> this._selectedRow;
    return this.userService.deleteUser(user.username);
  }

  private deleteComponent(): Observable<any> {
    const component: Component = <Component> this._selectedRow;
    return this.componentsService.deleteComponent(component.type);
  }
}
