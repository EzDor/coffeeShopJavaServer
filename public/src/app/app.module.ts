import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutes} from './routers';
import {AppComponent} from './app.component';
import {CoreModule} from '@core/core.module';
import {SharedModule} from '@shared/shared.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ShellModule} from './shell/shell.module';
import {HttpClientModule} from '@angular/common/http';
import {httpInterceptorProviders} from '@core/interceptors/interceptor-providers';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    CoreModule,
    SharedModule,
    AppRoutes,
    ShellModule,
  ],
  providers: [
    httpInterceptorProviders,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
