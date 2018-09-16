import {Component, OnInit} from '@angular/core';
import {DialogService} from 'src/app/core/services/dialog.service';
import {AdminService} from 'src/app/core/services/admin.service';
import {User} from 'src/app/models/user/user';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from 'src/app/core/services/user.service';
import {UpdateUser} from 'src/app/models/user/update-user';
import {Constants} from '../../../models/constants';
import {UserStatus} from '../../../models/user/user-status.enum';

@Component({
  selector: 'app-edit-user-dialog-form',
  templateUrl: './edit-user-dialog-form.component.html',
  styleUrls: ['./edit-user-dialog-form.component.css']
})
export class EditUserDialogFormComponent implements OnInit {

  public userForm: FormGroup;
  public loading: boolean;
  public user: User;
  public userStatus;

  constructor(private dialogService: DialogService,
              private adminService: AdminService,
              private formBuilder: FormBuilder,
              private userService: UserService) {
    this.loading = false;
    this.userStatus = UserStatus;
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
      () => () => this.editComplete(),
      error => this.showError(error)
    );
  }

  private updateUser(): void {
    const updatedUser: UpdateUser = {
      updatedUserDetails: this.userForm.value,
      password: Constants.ADMIN_UPDATE_USER_PASSWORD_KEY,
      usernameToUpdate: this.user.username,
    };

    // hack tired to change the validation at the server for empty password to admin update user.
    updatedUser.updatedUserDetails.password = Constants.ADMIN_UPDATE_USER_PASSWORD_KEY;

    this.userService.updateUser(updatedUser).subscribe(
      () => this.editComplete(),
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
        status: [this.user.status],
        admin: [this.user.admin],
      }
    );
  }

  private initNewUserForm(): void {
    this.userForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      status: [UserStatus.ACTIVE],
      admin: [''],
      password: ['', Validators.required],
    });
  }
}
