import {ComponentStatus} from './component-status.enum';

export interface Component {
  amount: number;

  name: string;

  type: string;

  price: number;

  status: ComponentStatus;

  image: string;
}
