import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Component as Comp} from '@models/component/component';
import {DialogService} from '@services/dialog.service';
import {Product} from '@models/product/product';
import {ComponentStatus} from '@models/component/component-status.enum';
import {OrderItem} from '@models/cart/order-item';
import {OrderService} from '@services/order.service';
import {CartService} from '@services/cart.service';
import {UpdatedOrderItem} from '@models/cart/updated-order-item';

@Component({
  selector: 'app-new-order-dialog-form',
  templateUrl: './new-order-dialog-form.component.html',
  styleUrls: ['./new-order-dialog-form.component.css']
})
export class NewOrderDialogFormComponent implements OnInit {

  public newOrderForm: FormGroup;
  public loading: boolean;
  public product: Product;
  public availableComponents: Comp[];
  public price: number;

  private isExistsItem: boolean;
  private orderItem: OrderItem;

  constructor(private dialogService: DialogService,
              private formBuilder: FormBuilder,
              private cartService: CartService,
              private orderService: OrderService) {
    this.loading = false;
    this.availableComponents = [];
  }

  ngOnInit() {
    this.orderItem = <OrderItem> this.cartService.selectedItem;
    this.product = this.orderItem.product;
    this.price = this.product.price;
    this.isExistsItem = !!this.orderItem.id;
    this.isExistsItem ? this.initExistOrderForm() : this.initNewOrderForm();
    this.availableComponents.sort();
  }

  public dismiss(): void {
    this.dialogService.dismissDialog();
  }

  public close(): void {
    this.dialogService.closeDialog();
  }

  public submitForm(): void {
    this.loading = true;
    const formValues = this.newOrderForm.value;
    const orderItem = this.getOrderItem(formValues);
    this.isExistsItem ? this.updateItemOnCart(orderItem) : this.addItemToCart(orderItem);
  }


  public isFieldInvalid(): boolean {
    return !Object.values(this.newOrderForm.value).includes(true);
  }

  public isOutOfStock(component: Comp): boolean {
    return component.status === ComponentStatus.OUT_OF_STOCK;
  }

  public isComponentAvailable(component): boolean {
    return component.status !== ComponentStatus.DISCARDED;
  }

  public getTooltip(component: Comp): string {
    return this.isOutOfStock(component) ? 'Out Of Stock' : '';
  }

  public checkboxChange(event, price: number) {
    if (event.target.checked) {
      this.price += price;
    }
    else {
      this.price -= price;
    }
  }


  /*********************************
   * Private Functions
   *********************************/

  private editComplete(): void {
    this.cartService.refreshDataTable();
    this.loading = false;
    this.close();
  }

  private showError(error: any): void {
    throw error;
  }


  private initNewOrderForm(): void {
    const formControlsObject: any = {};
    this.product.productComponents.forEach(
      (component: Comp) => {
        this.updateComponent(component, formControlsObject, false);
      }
    );
    this.newOrderForm = this.formBuilder.group(formControlsObject);
  }

  private initExistOrderForm(): void {
    const formControlsObject = {};
    this.product.productComponents.forEach(
      (component: Comp) => {
        const isComponentChecked: boolean = this.orderItem.componentsTypes.includes(component.type);
        this.updateComponent(component, formControlsObject, isComponentChecked);
        if (isComponentChecked) {
          this.price += component.price;
        }
      }
    );
    this.newOrderForm = this.formBuilder.group(formControlsObject);
  }

  private updateComponent(component: Comp, formControlsObject: any, state: boolean) {
    if (this.isComponentAvailable(component)) {
      this.availableComponents.push(component);
      formControlsObject[component.type] = [{value: state, disabled: this.isOutOfStock(component)}];
    }
  }


  private getOrderItem(formValues): OrderItem {
    const orderItem: OrderItem = {
      componentsTypes: [],
      productType: this.product.type,
    };
    Object.keys(formValues).forEach(
      (componentType: string) => {
        if (formValues[componentType]) {
          orderItem.componentsTypes.push(componentType);
        }
      }
    );
    return orderItem;
  }

  private addItemToCart(orderItem: OrderItem) {
    this.orderService.addItemToCart(orderItem).subscribe(
      () => this.editComplete(),
      (error) => this.showError(error)
    );
  }

  private updateItemOnCart(orderItem: OrderItem) {
    const updatedOrderItem: UpdatedOrderItem = {
      orderDetails: orderItem,
      orderItemId: this.orderItem.id
    };
    this.orderService.updateCart(updatedOrderItem).subscribe(
      () => this.editComplete(),
      (error) => this.showError(error)
    );
  }
}
