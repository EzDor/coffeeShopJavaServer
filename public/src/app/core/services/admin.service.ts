import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '@models/user/user';
import {Product} from '@models/product/product';
import {Component} from '@models/component/component';
import {AdminTabs} from '@models/admin/admin-tabs.enum';
import {UserService} from '@services/user.service';
import {ProductService} from '@services/product.service';
import {Constants} from '@models/constants';
import {CoreModule} from '@core/core.module';
import {ComponentsService} from '@services/components.service';
import {UserDisplayKeys} from '@models/user/user-display-keys';
import {ProductDisplayKeys} from '@models/product/product-display-keys';
import {ComponentDisplayKeys} from '@models/component/component-display-keys';

@Injectable({providedIn: CoreModule})
export class AdminService {

  private _currentTab: BehaviorSubject<AdminTabs>;
  private _currentTableData: BehaviorSubject<any[]>;
  private _rowDisplayKeys: BehaviorSubject<ComponentDisplayKeys | ProductDisplayKeys | UserDisplayKeys>;
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

  public get rowDisplayKeys(): BehaviorSubject<ComponentDisplayKeys | ProductDisplayKeys | UserDisplayKeys> {
    return this._rowDisplayKeys;
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
        throw Error('Error while trying to update data table.');
    }
  }

  private initUsersTable(): void {
    this._searchBy.next(Constants.ADMIN_TABLE_SEARCH_KEY_USERS);
    this._rowDisplayKeys.next(Constants.USER_DISPLAY_KEYS);
    this.userService.users.subscribe(
      (users: User[]) => {
        this._currentTableData.next(users);
      });
  }

  private initProductsTable(): void {
    this._searchBy.next(Constants.ADMIN_TABLE_SEARCH_KEY_PRODUCT);
    this._rowDisplayKeys.next(Constants.PRODUCT_DISPLAY_KEYS);
    this.productService.getProducts()
      .subscribe(
        (products: Product[]) => {
          this.mapProductComponentToProductType(products);
          this._currentTableData.next(products);
        }
      );
  }

  private mapProductComponentToProductType(products: Product[]) {
    products.map(product => {
      product.componentsTypes = product.productComponents.map(component => component.type);
    });
  }

  private initComponentsTable(): void {
    this._searchBy.next(Constants.ADMIN_TABLE_SEARCH_KEY_COMPONENT);
    this._rowDisplayKeys.next(Constants.COMPONENT_DISPLAY_KEYS);
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
    this._rowDisplayKeys = new BehaviorSubject<ProductDisplayKeys | ComponentDisplayKeys | UserDisplayKeys>(Constants.USER_DISPLAY_KEYS);
  }

  private deleteUser(): Observable<any> {
    const user: User = <User> this._selectedRow;
    return this.userService.deleteUser(user.username);
  }

  private deleteProduct(): Observable<any> {
    const product: Product = <Product> this._selectedRow;
    return this.productService.deleteProduct(product.type);
  }

  private deleteComponent(): Observable<any> {
    const component: Component = <Component> this._selectedRow;
    return this.componentsService.deleteComponent(component.type);
  }
}
