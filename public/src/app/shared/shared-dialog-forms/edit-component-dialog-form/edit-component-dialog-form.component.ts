import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DialogService} from 'src/app/core/services/dialog.service';
import {AdminService} from 'src/app/core/services/admin.service';
import {Component as Comp} from 'src/app/models/component/component';
import {ComponentsService} from '../../../core/services/components.service';
import {ComponentStatus} from '../../../models/component/component-status.enum';
import {UpdateComponent} from '../../../models/component/update-component';

@Component({
  selector: 'app-edit-component-dialog-form',
  templateUrl: './edit-component-dialog-form.component.html',
  styleUrls: ['./edit-component-dialog-form.component.css']
})
export class EditComponentDialogFormComponent implements OnInit {

  public componentForm: FormGroup;
  public loading: boolean;
  public component: Comp;
  public componentStatus;

  constructor(private dialogService: DialogService,
              private adminService: AdminService,
              private formBuilder: FormBuilder,
              private componentsService: ComponentsService) {
    this.loading = false;
  }

  ngOnInit() {
    this.component = <Comp> this.adminService.selectedRow;
    this.component ? this.initExistsComponentForm() : this.initNewComponentForm();
    this.componentStatus = ComponentStatus;
  }

  public dismiss(): void {
    this.dialogService.dismissDialog();
  }

  public close(): void {
    this.dialogService.closeDialog();
  }

  public submitForm(): void {
    this.loading = true;
    if (this.component) {
      this.updateComponent();
    }
    else {
      this.createComponent();
    }

  }

  public isFieldInvalid(field: string) {
    return this.componentForm.get(field).invalid && this.componentForm.get(field).dirty;
  }


  /*********************************
   * Private Functions
   *********************************/

  private createComponent(): void {
    const newComponent: Comp = this.componentForm.value;
    this.componentsService.createComponent(newComponent).subscribe(
      res => this.editComplete(),
      error => this.showError(error)
    );
  }

  private updateComponent(): void {
    const updatedComponent: UpdateComponent = {
      componentTypeToUpdate: this.component.type,
      updatedComponentDetails: this.componentForm.value
    };

    this.componentsService.updateComponent(updatedComponent).subscribe(
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

  private initExistsComponentForm() {
    this.componentForm = this.formBuilder.group({
        name: [this.component.name, Validators.required],
        type: [this.component.type, Validators.required],
        price: [this.component.price, Validators.required],
        amount: [this.component.amount, Validators.required],
        status: [this.component.status],
        image: [this.component.image, Validators.required],
      }
    );
  }

  private initNewComponentForm(): void {
    this.componentForm = this.formBuilder.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      price: ['', Validators.required],
      amount: ['', Validators.required],
      status: [ComponentStatus.ACTIVE],
      image: ['', Validators.required],
    });
  }

}
