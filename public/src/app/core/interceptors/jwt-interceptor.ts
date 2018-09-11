import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Constants} from 'src/app/models/constants';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    const currentUser = JSON.parse(localStorage.getItem(Constants.CURRENT_USER));
    if (currentUser && currentUser.token) {
      request = request.clone({
        setHeaders: {
          Authorization: currentUser.token
        }
      });
    }

    return next.handle(request);
  }
}

