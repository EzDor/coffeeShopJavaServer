import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Product} from 'src/app/models/product/product';
import {DialogService} from 'src/app/core/services/dialog.service';
import {AdminService} from 'src/app/core/services/admin.service';
import {ComponentsService} from 'src/app/core/services/components.service';
import {ProductStatus} from 'src/app/models/product/product-status.enum';
import {ProductService} from 'src/app/core/services/product.service';
import {Component as Comp} from 'src/app/models/component/component';
import {UpdatedProduct} from '../../../models/product/updated-product';

@Component({
  selector: 'app-edit-product-dialog-form',
  templateUrl: './edit-product-dialog-form.component.html',
  styleUrls: ['./edit-product-dialog-form.component.css']
})
export class EditProductDialogFormComponent implements OnInit {

  public productForm: FormGroup;
  public loading: boolean;
  public product: Product;
  public availableComponents: Comp[];
  public productStatus;
  public filteredComponents: string[];


  constructor(private dialogService: DialogService,
              private adminService: AdminService,
              private formBuilder: FormBuilder,
              private componentsService: ComponentsService,
              private productService: ProductService) {
    this.loading = false;
  }

  ngOnInit() {
    this.product = <Product> this.adminService.selectedRow;
    this.product ? this.initExistsProductForm() : this.initNewProductForm();
    this.productStatus = ProductStatus;
    this.componentsService.getComponents().subscribe(
      (components: Comp[]) => {
        this.availableComponents = components;
      },
      error => {
        this.showError(error);
      }
    );
  }

  filterComponentMultiple(event) {
    const query = event.query;

    this.filteredComponents = query ? this.filterComponent(query) : this.availableComponents.map(component => component.type);
  }

  public filterComponent(query): string[] {

    const filtered: string[] = [];
    for (let i = 0; i < this.availableComponents.length; i++) {
      const componentType = this.availableComponents[i].type;
      if (componentType.indexOf(query.toLowerCase()) >= 0 && !this.productForm.value.componentsTypes.includes(componentType)) {
        filtered.push(componentType);
      }
    }
    return filtered;
  }

  public dismiss(): void {
    this.dialogService.dismissDialog();
  }

  public close(): void {
    this.dialogService.closeDialog();
  }

  public submitForm(): void {
    this.loading = true;
    if (this.product) {
      this.updateProduct();
    }
    else {
      this.createProduct();
    }

  }

  public isFieldInvalid(field: string) {
    return this.productForm.get(field).invalid && this.productForm.get(field).dirty;
  }


  /*********************************
   * Private Functions
   *********************************/

  private createProduct(): void {
    const newProduct: Product = this.productForm.value;
    this.productService.createProduct(newProduct).subscribe(
      () => this.editComplete(),
      error => this.showError(error)
    );
  }

  private updateProduct(): void {
    const updatedProduct: UpdatedProduct = {
      productTypeToUpdate: this.product.type,
      updatedProductDetails: this.productForm.value
    };

    this.productService.updateProduct(updatedProduct).subscribe(
      res => this.editComplete(),
      error => this.showError(error)
    );
  }

  private editComplete(): void {
    this.loading = false;
    this.close();
    this.adminService.refreshDataTable();
  }

  private showError(error: any): void {
    throw error;
  }

  private initExistsProductForm() {
    this.productForm = this.formBuilder.group({
        name: [this.product.name, Validators.required],
        type: [this.product.type, Validators.required],
        price: [this.product.price, Validators.required],
        description: [this.product.description, Validators.required],
        image: [this.product.image, Validators.required],
        componentsTypes: [this.product.componentsTypes, Validators.required],
        status: [this.product.status],
      }
    );
  }

  private initNewProductForm(): void {
    this.productForm = this.formBuilder.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      price: ['', Validators.required],
      description: ['', Validators.required],
      image: ['', Validators.required],
      componentsTypes: ['', Validators.required],
      status: [ProductStatus.ACTIVE],
    });
  }
}
