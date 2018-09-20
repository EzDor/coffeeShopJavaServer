import {UserDisplayKeys} from './user/user-display-keys';
import {ProductDisplayKeys} from './product/product-display-keys';
import {ComponentDisplayKeys} from './component/component-display-keys';
import {OrderItemDisplayKeys} from '@models/cart/order-item-display-keys';
import {OrderDisplayKeys} from '@models/cart/order-display-keys';

export class Constants {

  /****************************
   * General constants
   ****************************/
  public static readonly CURRENT_USER: string = 'currentUser';
  public static readonly ROLE_ADMIN: string = 'ROLE_ADMIN';
  public static readonly NUM_OF_GRID_COL: number = 4;
  public static readonly GRID_HEIGHT: number = 100;
  public static readonly NUM_OF_GRID_COL_FOR_CARD: number = 2;
  public static readonly NUM_OF_GRID_ROWS_FOR_CARD: number = 4;

  /****************************
   * Admin Page
   ****************************/
  public static readonly ADMIN_TAB_PRODUCT_LABEL: string = 'Products';
  public static readonly ADMIN_TAB_PRODUCT_ICON: string = 'fa fa-cutlery';
  public static readonly ADMIN_TAB_COMPONENT_LABEL: string = 'Components';
  public static readonly ADMIN_TAB_COMPONENT_ICON: string = 'fa fa-leaf';
  public static readonly ADMIN_TAB_USERS_LABEL: string = 'Users';
  public static readonly ADMIN_TAB_USERS_ICON: string = 'fa fa-users';
  public static readonly ADMIN_TABLE_SEARCH_KEY_USERS: string = 'username';
  public static readonly ADMIN_TABLE_SEARCH_KEY_PRODUCT: string = 'type';
  public static readonly ADMIN_TABLE_SEARCH_KEY_COMPONENT: string = 'type';
  public static readonly ADMIN_UPDATE_USER_PASSWORD_KEY: string = 'password';


  /****************************
   * Cart Page
   ****************************/
  public static readonly CART_TAB_LABEL: string = 'Cart';
  public static readonly CART_TAB_ICON: string = 'fa fa-cart-plus';
  public static readonly CART_TAB_HISTORY_LABEL: string = 'Order History';
  public static readonly CART_TAB_HISTORY_ICON: string = 'fa fa-history';
  public static readonly CART_TABLE_SEARCH_KEY: string = 'type';
  public static readonly CART_TABLE_SEARCH_KEY_HISTORY: string = 'type';
  public static readonly CART_TABLE_PRIMARY_BUTTON_KEY: string = 'Edit';
  public static readonly CART_TABLE_HISTORY_BUTTON_KEY: string = 'Details';


  /****************************
   * Order
   ****************************/
  public static readonly ORDER_ITEM_DISPLAY_KEYS: OrderItemDisplayKeys = {
    id: 'Item Id',
    productType: 'Type',
    price: 'Price',
    componentsTypes: 'Chosen Components',
  };

  public static readonly ORDER_DISPLAY_KEYS: OrderDisplayKeys = {
    id: 'Item Id',
    displayDate: 'Date',
    price: 'Price',
    orderItemProducts: 'Items',
  };

  /****************************
   * Product
   ****************************/
  public static readonly PRODUCT_DISPLAY_KEYS: ProductDisplayKeys = {
    id: 'Id',
    name: 'Name',
    type: 'Product Type',
    description: 'Description',
    price: 'Price',
    componentsTypes: 'Components Types',
    status: 'Status',
  };

  /****************************
   * Component
   ****************************/
  public static readonly COMPONENT_DISPLAY_KEYS: ComponentDisplayKeys = {
    id: 'Id',
    name: 'Name',
    type: 'Component Type',
    price: 'Price',
    amount: 'Amount',
    status: 'Status',
  };

  /****************************
   * User
   ****************************/
  public static readonly USER_DISPLAY_KEYS: UserDisplayKeys = {
    id: 'Id',
    firstName: 'First Name',
    lastName: 'Last Name',
    username: 'User Name',
    admin: 'Admin',
    status: 'Status',
  };


  /****************************
   * Internal routing paths
   ****************************/
  public static readonly LOGIN_LAZY_MODULE_PATH: string = '@app/login/login.module#LoginModule';
  public static readonly SIGN_IN_COMPONENT_FULL_PATH: string = 'login/sign-in';
  public static readonly REGISTER_COMPONENT_FULL_PATH: string = '/login/register';
  public static readonly SIGN_IN_COMPONENT_PATH: string = 'sign-in';
  public static readonly REGISTER_COMPONENT_PATH: string = 'register';
  public static readonly LOGIN_PATH: string = 'login';

  public static readonly HOME_PATH: string = '';
  public static readonly HOME_LAZY_MODULE_PATH: string = '@app/home/home.module#HomeModule';

  public static readonly ADMIN_LAZY_MODULE_PATH: string = '@app/home/admin/admin.module#AdminModule';
  public static readonly ADMIN_COMPONENT_PATH: string = 'admin';

  public static readonly CART_COMPONENT_PATH: string = 'cart';

  public static readonly OTHERWISE_PATH: string = '**';


  /****************************
   * Server API calls
   ****************************/

  /* General */
  public static readonly BASE_URL: string = 'http://localhost:8081';
  public static readonly API_PREFIX: string = '/api';

  /* Users */
  public static readonly SIGN_UP_API_CALL: string = '/users/signUp';
  public static readonly GET_USERS_API_CALL: string = '/users/';
  public static readonly UPDATE_USER_API_CALL: string = '/users/update';
  public static readonly DELETE_USER_API_CALL: string = '/users/delete';

  /* Login */
  public static readonly LOGIN_API_CALL: string = '/login';

  /* Product */
  public static readonly GET_ALL_PRODUCTS_API_CALL: string = '/product';
  public static readonly GET_ACTIVE_PRODUCTS_API_CALL: string = '/product/active';
  public static readonly UPDATE_PRODUCT_API_CALL: string = '/product/update';
  public static readonly CREATE_PRODUCT_API_CALL: string = '/product/create';
  public static readonly DELETE_PRODUCT_API_CALL: string = '/product/delete';

  /* Component */
  public static readonly GET_COMPONENTS_API_CALL: string = '/component';
  public static readonly CREATE_COMPONENTS_API_CALL: string = '/component/create';
  public static readonly UPDATE_COMPONENTS_API_CALL: string = '/component/update';
  public static readonly DELETE_COMPONENTS_API_CALL: string = '/component/delete';

  /* Cart */
  public static readonly GET_ACTIVE_CART_API_CALL: string = '/cart';
  public static readonly ADD_ITEM_TO_CART_API_CALL: string = '/cart/add';
  public static readonly CHECKOUT_CART_API_CALL: string = '/cart/checkout';
  public static readonly UPDATE_ITEM_ON_CART_API_CALL: string = '/cart/update';
  public static readonly DELETE_ITEM_FROM_CART_API_CALL: string = '/cart/delete';
  public static readonly GET_ARCHIVE_ORDERS_FROM_CART_API_CALL: string = '/cart/archive';

}
