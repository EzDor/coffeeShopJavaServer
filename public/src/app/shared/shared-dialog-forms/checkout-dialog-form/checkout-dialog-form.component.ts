import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Component as Comp} from '@models/component/component';
import {DialogService} from '@services/dialog.service';
import {AdminService} from '@services/admin.service';
import {ComponentsService} from '@services/components.service';
import {ComponentStatus} from '@models/component/component-status.enum';
import {UpdateComponent} from '@models/component/update-component';
import {CartService} from '@services/cart.service';
import {init} from 'protractor/built/launcher';
import {OrderItem} from '@models/cart/order-item';
import {OrderService} from '@services/order.service';
import {CreditCard} from '@models/cart/credit-card';

@Component({
  selector: 'app-checkout-dialog-form',
  templateUrl: './checkout-dialog-form.component.html',
  styleUrls: ['./checkout-dialog-form.component.css']
})
export class CheckoutDialogFormComponent implements OnInit {


  public checkoutForm: FormGroup;
  public loading: boolean;
  public price: number;

  constructor(private dialogService: DialogService,
              private cartService: CartService,
              private orderService: OrderService,
              private formBuilder: FormBuilder) {
    this.loading = false;
  }

  ngOnInit() {
    this.initTotalPrice();
    this.initForm();
  }

  public dismiss(): void {
    this.dialogService.dismissDialog();
  }

  public close(): void {
    this.dialogService.closeDialog();
  }

  public checkout(): void {
    this.loading = true;
    const creditCard: CreditCard = this.checkoutForm.value;
    this.orderService.checkout(creditCard).subscribe(
      () => this.editComplete(),
      (error) => this.showError(error)
    );
  }

  public isFieldInvalid(field: string) {
    return this.checkoutForm.get(field).invalid && this.checkoutForm.get(field).dirty;
  }


  /*********************************
   * Private Functions
   *********************************/

  private editComplete(): void {
    this.loading = false;
    this.close();
    this.cartService.refreshDataTable();
  }

  private showError(error: any): void {
    throw error;
  }

  private initForm(): void {
    this.checkoutForm = this.formBuilder.group({
      creditNumber: ['', Validators.required],
      expireDate: ['', Validators.required],
      cvv: ['', Validators.required],
    });
  }

  private initTotalPrice(): void {
    this.price = 0;
    const orderItems: OrderItem [] = <OrderItem[]>this.cartService.currentTableData.value;
    orderItems.forEach(
      (orderItem: OrderItem) => {
        this.price += orderItem.price;
      }
    );
  }

}
