import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {UserService} from '../core/services/user.service';
import {User} from '../models/user/user';
import {Constants} from '../models/constants';

@Component({
  styleUrls: ['./register.component.css'],
  templateUrl: './register.component.html'
})

export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  firstName: FormControl;
  lastName: FormControl;
  username: FormControl;
  password: FormControl;
  saving: boolean;

  constructor(private router: Router, private userService: UserService) {
    this.saving = false;
  }

  ngOnInit() {
    this.firstName = new FormControl('', Validators.required);
    this.lastName = new FormControl('', Validators.required);
    this.username = new FormControl('', Validators.required);
    this.password = new FormControl('', Validators.required);

    this.registerForm = new FormGroup({
      firstName: this.firstName,
      lastName: this.lastName,
      username: this.username,
      password: this.password
    });
  }

  public registerUser(user) {
    this.saving = true;
    this.saveAndRedirect(user);
  }

  public cancel() {
    this.router.navigate(['/']);
  }

  private saveAndRedirect(user: User) {
    this.userService.createUser(user)
      .subscribe((body) => {
          console.log(body);
          this.saving = false;
          this.router.navigate([Constants.SIGN_IN_COMPONENT_FULL_PATH]);
        },
        (err) => {
          throw err;
        }
      );
  }
}
