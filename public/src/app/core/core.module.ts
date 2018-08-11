import {NgModule, Optional, SkipSelf} from '@angular/core';
import {RouterModule} from '@angular/router';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {HttpClientModule} from '@angular/common/http';
import {SharedModule} from '../shared/shared.module';
import {AuthenticationService} from './auth/auth.service';
import {UserService} from '../users/user.service';


@NgModule({
  imports: [
    HttpClientModule,
    RouterModule,
    SharedModule
  ],
  exports: [
    NavBarComponent,
    HttpClientModule,
    RouterModule
  ],
  providers: [
    AuthenticationService,
    UserService,
  ],
  declarations: [
    NavBarComponent,
  ]
})

export class CoreModule {
  /* make sure CoreModule is imported only by one NgModule the AppModule */
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule is already loaded. Import only in AppModule');
    }
  }
}
