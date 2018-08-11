export class Constants {

  /**
   * General constants
   * */
  public static readonly CURRENT_USER: string = 'currentUser';
  public static readonly NUM_OF_GRID_COL: number = 4;
  public static readonly GRID_HEIGHT: number = 100;
  public static readonly NUM_OF_GRID_COL_FOR_CARD: number = 2;
  public static readonly NUM_OF_GRID_ROWS_FOR_CARD: number = 4;


  /**
   * Internal routing paths
   * */
  public static readonly SIGN_IN_COMPONENT_PATH: string = '/login/sign-in';

  /**
   * Server API calls
   * */
  public static readonly BASE_URL: string = 'http://localhost:8081';
  public static readonly SIGN_UP_API_CALL: string = '/users/signUp';
  public static readonly API_PREFIX: string = '/api';
  public static readonly LOGIN_API_CALL: string = '/login';
  public static readonly GET_PRODUCTS_API_CALL: string = '/product';

}
