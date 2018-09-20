import {NgModule, Optional, SkipSelf} from '@angular/core';
import {SharedModule} from 'src/app/shared/shared.module';
import {MessageService} from 'primeng/api';



@NgModule({
  imports: [
    SharedModule,
  ],
  exports: [
  ],
  providers: [
    MessageService,
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
