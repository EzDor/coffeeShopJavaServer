import {NgModule, Optional, SkipSelf} from '@angular/core';
import {SharedModule} from 'src/app/shared/shared.module';



@NgModule({
  imports: [
    SharedModule,
  ],
  exports: [
  ],
  providers: [
  ],
  declarations: [
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
