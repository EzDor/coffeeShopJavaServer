import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Component as Comp} from '@models/component/component';
import {DialogService} from '@services/dialog.service';
import {Product} from '@models/product/product';
import {ProductService} from '@services/product.service';
import {ComponentStatus} from '@models/component/component-status.enum';
import {OrderItem} from '@models/cart/order-item';
import {CartService} from '@services/cart.service';

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

  constructor(private dialogService: DialogService,
              private formBuilder: FormBuilder,
              private productService: ProductService,
              private cartService: CartService) {
    this.loading = false;
    this.availableComponents = [];
  }

  ngOnInit() {
    this.product = this.productService.selectedProduct;
    this.price = this.product.price;
    this.initNewOrderForm();
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

    this.cartService.addItemToCart(orderItem).subscribe(
      () => this.editComplete(),
      (error) => this.showError(error)
    );
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
    this.loading = false;
    this.close();
  }

  private showError(error: any): void {
    throw error;
  }


  private initNewOrderForm(): void {
    const formControlsObject = {};
    this.product.productComponents.forEach(
      (component: Comp) => {
        if (this.isComponentAvailable(component)) {
          this.availableComponents.push(component);
          formControlsObject[component.type] = [{value: '', disabled: this.isOutOfStock(component)}];
        }
      }
    );
    this.newOrderForm = this.formBuilder.group(formControlsObject);
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

}
