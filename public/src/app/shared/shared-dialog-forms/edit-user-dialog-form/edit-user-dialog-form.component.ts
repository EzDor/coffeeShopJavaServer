import {Component, OnInit} from '@angular/core';
import {DialogService} from 'src/app/core/services/dialog.service';
import {AdminService} from 'src/app/core/services/admin.service';
import {User} from 'src/app/models/users/user.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from 'src/app/core/services/user.service';
import {UpdateUser} from 'src/app/models/users/update-user';
import {from, Observable} from 'rxjs';
import {concatMap} from 'rxjs/operators';
import {Constants} from '../../../models/constants';

@Component({
  selector: 'app-edit-user-dialog-form',
  templateUrl: './edit-user-dialog-form.component.html',
  styleUrls: ['./edit-user-dialog-form.component.css']
})
export class EditUserDialogFormComponent implements OnInit {

  public userForm: FormGroup;
  public loading: boolean;
  public user: User;

  constructor(private dialogService: DialogService,
              private adminService: AdminService,
              private formBuilder: FormBuilder,
              private userService: UserService) {
    this.loading = false;
  }

  ngOnInit() {
    this.user = <User> this.adminService.selectedRow;
    this.user ? this.initExistsUserForm() : this.initNewUserForm();
  }

  public dismiss(): void {
    this.dialogService.dismissDialog();
  }

  public close(): void {
    this.dialogService.closeDialog();
  }

  public submitForm(): void {
    this.loading = true;
    if (this.user) {
      this.updateUser();
    }
    else {
      this.addUser();
    }

  }

  public isFieldInvalid(field: string) {
    return this.userForm.get(field).invalid && this.userForm.get(field).dirty;
  }


  /*********************************
   * Private Functions
   *********************************/

  private addUser(): void {
    const newUser: User = this.userForm.value;
    this.userService.createUser(newUser).subscribe(
      res => this.successfulResponse(newUser),
      error => this.showError(error)
    );
  }

  private updateUser(): void {
    const updatedUser: UpdateUser = {
      updatedUserDetails: this.userForm.value,
      password: Constants.ADMIN_UPDATE_USER_PASSWORD_KEY,
      usernameToUpdate: this.user.username,
    };

    this.userService.updateUser(updatedUser).subscribe(
      res => this.successfulResponse(this.userForm.value),
      error => this.showError(error)
    );
  }

  private successfulResponse(user: User): void {
    if (user.admin) {
      this.updateAdminPermissions(user.username);
    }
    else {
      this.editComplete();
    }
  }

  private updateAdminPermissions(username: string): void {
    this.userService.updateUserPermissions(username).subscribe(
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

  private initExistsUserForm() {
    this.userForm = this.formBuilder.group({
        firstName: [this.user.firstName, Validators.required],
        lastName: [this.user.lastName, Validators.required],
        username: [this.user.username, Validators.required],
        admin: [this.user.admin],
      }
    );
  }

  private initNewUserForm(): void {
    this.userForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      admin: [''],
      password: ['', Validators.required],
    });
  }
}
