import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../core/services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.get_token();
    if (token) {
      const headers = req.headers.set('Authorization', `Bearer ${token}`);
      const modifiedReq = req.clone({ headers });
      return next.handle(modifiedReq);
    } else {
      return next.handle(req);
    }
  }
}
