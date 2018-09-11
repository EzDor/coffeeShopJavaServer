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
   * Internal routing paths
   ****************************/
  public static readonly SIGN_IN_COMPONENT_FULL_PATH: string = 'login/sign-in';
  public static readonly SIGN_IN_COMPONENT_PATH: string = 'sign-in';
  public static readonly REGISTER_COMPONENT_PATH: string = 'register';
  public static readonly HOME_PATH: string = '';
  public static readonly LOGIN_PATH: string = 'login';
  public static readonly HOME_LAZY_MODULE_PATH: string = './home/home.module#HomeModule';
  public static readonly ADMIN_LAZY_MODULE_PATH: string = './home/admin/admin.module#AdminModule';
  public static readonly LOGIN_LAZY_MODULE_PATH: string = './login/login.module#LoginModule';
  public static readonly OTHERWISE_PATH: string = '**';
  public static readonly ADMIN_COMPONENT_PATH: string = 'admin';


  /****************************
   * Server API calls
   ****************************/
    // General
  public static readonly BASE_URL: string = 'http://localhost:8081';
  public static readonly API_PREFIX: string = '/api';
  // Users
  public static readonly SIGN_UP_API_CALL: string = '/users/signUp';
  public static readonly GET_USERS_API_CALL: string = '/users/';
  public static readonly UPDATE_USER_API_CALL: string = '/users/update';
  public static readonly UPDATE_USER_PERMISSIONS_API_CALL: string = '/users/admin';
  public static readonly DELETE_USER_API_CALL: string = '/users/delete';
  // Login
  public static readonly LOGIN_API_CALL: string = '/login';
  // Product
  public static readonly GET_PRODUCTS_API_CALL: string = '/product';
  // Component
  public static readonly GET_COMPONENTS_API_CALL: string = '/component';
  public static readonly CREATE_COMPONENTS_API_CALL: string = '/component/create';
  public static readonly UPDATE_COMPONENTS_API_CALL: string = '/component/update';

}
